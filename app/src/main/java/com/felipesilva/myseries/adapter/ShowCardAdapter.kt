package com.felipesilva.myseries.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.felipesilva.myseries.R
import com.felipesilva.myseries.adapter.viewHolder.CardViewHolder
import com.felipesilva.myseries.data.Shows

class ShowCardAdapter(private val shows: MutableList<Shows>) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_show, parent, false)
        return CardViewHolder(view)

    }

    override fun getItemCount() = shows.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val show = shows[position].show

        if (holder is CardViewHolder)
            holder.bind(show)
    }

}
