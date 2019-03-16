package com.felipesilva.myseries.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.felipesilva.myseries.R
import com.felipesilva.myseries.data.Show
import com.felipesilva.myseries.data.Shows
import kotlinx.android.synthetic.main.card_show.view.*

class ShowCardAdapter(private val shows: MutableList<Shows>) : RecyclerView.Adapter<ShowCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_show, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = shows[position].show

        if (holder is ViewHolder)
            holder.bind(show)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView : CardView = itemView.card_show
        val imageViewPoster: ImageView = itemView.image_show_poster
        val textViewTitle: TextView = itemView.text_show_title
        val textViewGenres: TextView = itemView.text_show_genres

        fun bind(show: Show) {
            show.image?.let {
                Glide
                    .with(imageViewPoster.context)
                    .load(it.medium)
                    .into(imageViewPoster)
            }

            textViewTitle.text = show.name
            textViewGenres.text = show.genres.toString()
        }
    }

}
