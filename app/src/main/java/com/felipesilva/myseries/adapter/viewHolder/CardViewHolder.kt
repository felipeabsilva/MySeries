package com.felipesilva.myseries.adapter.viewHolder

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.felipesilva.myseries.R
import com.felipesilva.myseries.data.Show
import com.felipesilva.myseries.mvp.view.DetailsShowActivity
import kotlinx.android.synthetic.main.card_show.view.*
import kotlinx.android.synthetic.main.details_show.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.text.SimpleDateFormat
import java.util.*

class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cardView : CardView = itemView.card_show
    val imageViewPoster: ImageView = itemView.image_show_poster
    val textViewTitle: TextView = itemView.text_show_title
    val textViewGenres: TextView = itemView.text_show_genres
    val imageViewFavorite: ImageView = itemView.icon_favorite_show

    fun bind(show: Show, listFavorite: MutableSet<String>) {
        textViewTitle.text = show.name

        if (isFavorite(show.name, listFavorite))
            imageViewFavorite.setImageResource(R.drawable.ic_favorite_applied)
        else
            imageViewFavorite.setImageResource(R.drawable.ic_favorite_not_applied)

        show.image?.let {
            Glide
                .with(imageViewPoster.context)
                .load(it.medium)
                .into(imageViewPoster)
        }

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

            if (isFavorite(show.name, listFavorite))
                intent.putExtra("favorite", true)

            intent.putExtra("title", show.name)
            intent.putExtra("genres", "Genres: ${formatGenres(show.genres)}")
            intent.putExtra("release", "Released in ${formatDate(show.premiered)}")
            intent.putExtra("summary", formatSummary("Synopsis: ${formatSummary(show.summary)}"))

            it.context.startActivity(intent)
        }

        imageViewFavorite.setOnClickListener {
            val FILE_NAME = "favorite_list"
            val file : File = it.context.getFileStreamPath(FILE_NAME)

            listFavorite.add(show.name)

            val fos = FileOutputStream(file)
            val oos = ObjectOutputStream(fos)

            oos.writeObject(listFavorite)
            oos.close()
            fos.close()

            imageViewFavorite.setImageResource(R.drawable.ic_favorite_applied)
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

    private fun formatGenres(genres: MutableList<String>) : String {
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

    fun isFavorite(name: String, listFavorite: MutableSet<String>) = listFavorite.filter { it.equals(name) }.any()
}