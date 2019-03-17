package com.felipesilva.myseries.adapter

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.felipesilva.myseries.R
import com.felipesilva.myseries.adapter.viewHolder.CardViewHolder
import com.felipesilva.myseries.data.Show
import com.felipesilva.myseries.data.Shows
import com.felipesilva.myseries.mvp.view.DetailsShowActivity
import kotlinx.android.synthetic.main.card_show.view.*

class ShowCardAdapter(private val shows: MutableList<Shows>, private val favoriteList: MutableList<String>) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_show, parent, false)
        return CardViewHolder(view)

    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val show = shows[position].show
        val favorite = favoriteList.filter { equals(show.name) }.any()

        if (holder is CardViewHolder)
            holder.bind(show, favorite)
    }

}
