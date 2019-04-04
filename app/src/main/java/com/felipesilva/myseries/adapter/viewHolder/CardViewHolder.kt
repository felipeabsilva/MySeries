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
import java.text.SimpleDateFormat
import java.util.*

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

        formatGenres(show.genres).apply {
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
            intent.putExtra("genres", "Genres: ${formatGenres(show.genres)}")
            intent.putExtra("release", "Released in ${formatDate(show.premiered)}")
            intent.putExtra("summary", formatSummary("Synopsis: ${formatSummary(show.summary)}"))

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

    private fun formatSummary(summary: String) : String{
        val re = Regex("<.*?>")
        return summary.replace(re, "")
    }

    private fun formatDate(date: Date) : String {
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }

    private fun formatGenres(genres: List<String>) : String {
        val formattedGenres = StringBuilder()

        if (genres.isNotEmpty()) {
            genres.forEachIndexed{ index, genre ->
                if(index != (genres.size - 1))
                    formattedGenres.append("$genre, ")
                else
                    formattedGenres.append(genre)
            }
        } else
            formattedGenres.append("Not assigned")

        return formattedGenres.toString()
    }
}