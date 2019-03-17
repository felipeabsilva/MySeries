package com.felipesilva.myseries.mvp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.felipesilva.myseries.R
import com.felipesilva.myseries.features.FavoriteShow
import kotlinx.android.synthetic.main.details_show.*


class DetailsShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_show)

        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val genres = intent.getStringExtra("genres")
        val release = intent.getStringExtra("release")
        val summary = intent.getStringExtra("summary")
        val favorite = intent.getBooleanExtra("favorite", false)

        setupActionBar(title)

        image?.let {
            Glide
                .with(image_show_poster_details.context)
                .load(image)
                .into(image_show_poster_details)
        }

        text_show_title_details.text = title
        text_show_genres_details.text = genres
        text_show_release_details.text = release
        text_show_summary_details.text = summary

        if (favorite)
            icon_favorite_show_details.setImageResource(R.drawable.ic_favorite_applied)
        else
            icon_favorite_show_details.setImageResource(R.drawable.ic_favorite_not_applied)

        val imageViewFavorite : ImageView = icon_favorite_show_details

        imageViewFavorite.setOnClickListener {
            if (FavoriteShow.isFavorite(title)) {
                FavoriteShow.removeFavorite(title, it.context)
                imageViewFavorite.setImageResource(R.drawable.ic_favorite_not_applied)
            } else {
                FavoriteShow.addFavorite(title, it.context)
                imageViewFavorite.setImageResource(R.drawable.ic_favorite_applied)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupActionBar(it: String) {
        val actionBar = supportActionBar
        actionBar?.title = it
    }
}