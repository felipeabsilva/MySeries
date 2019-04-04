package com.felipesilva.myseries.adapter.viewHolder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.felipesilva.myseries.R
import com.felipesilva.myseries.data.model.Show
import com.felipesilva.myseries.features.FavoriteShow
import com.felipesilva.myseries.mvp.view.DetailsShowActivity
import kotlinx.android.synthetic.main.card_show.view.*
import com.felipesilva.myseries.utilities.*

class CardViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    private val cardView : androidx.cardview.widget.CardView = itemView.card_show
    private val imageViewPoster: ImageView = itemView.image_show_poster
    private val textViewTitle: TextView = itemView.text_show_title
    private val textViewGenres: TextView = itemView.text_show_genres
    private val imageViewFavorite: ImageView = itemView.icon_favorite_show

    fun bind(show: Show) {
        textViewTitle.text = show.name

        if (FavoriteShow.isFavorite(show.name))
            imageViewFavorite.setImageResource(R.drawable.ic_favorite_applied)
        else
            imageViewFavorite.setImageResource(R.drawable.ic_favorite_not_applied)

        if (show.image?.medium != null) {
            Glide
                .with(imageViewPoster.context)
                .load(show.image.medium)
                .into(imageViewPoster)
        } else
            imageViewPoster.setImageResource(R.drawable.ic_app_stock)

        show.genres.formatGenres().apply {
            if (equals("Not assigned"))
                textViewGenres.text = ""
            else
                textViewGenres.text = this
        }

        cardView.setOnClickListener {
            val intent = Intent(it.context, DetailsShowActivity::class.java)

            show.image?.original?.let {
                intent.putExtra("image", show.image.original)
            }

            if (FavoriteShow.isFavorite(show.name))
                intent.putExtra("favorite", true)

            intent.putExtra("title", show.name)
            intent.putExtra("genres", "Genres: ${show.genres.formatGenres()}")
            intent.putExtra("release", "Released in ${show.premiered.formatDate()}")
            intent.putExtra("summary", "Synopsis: ${show.summary.formatSummary()}}")

            it.context.startActivity(intent)
        }

        imageViewFavorite.setOnClickListener {
            if (FavoriteShow.isFavorite(show.name)) {
                FavoriteShow.removeFavorite(show.name, it.context)
                imageViewFavorite.setImageResource(R.drawable.ic_favorite_not_applied)
            } else {
                FavoriteShow.addFavorite(show.name, it.context)
                imageViewFavorite.setImageResource(R.drawable.ic_favorite_applied)
            }
        }
    }
}