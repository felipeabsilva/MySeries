package com.felipesilva.myseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.felipesilva.myseries.R
import com.felipesilva.myseries.adapter.viewHolder.CardViewHolder
import com.felipesilva.myseries.data.model.Shows

class ShowCardAdapter(private val shows: LiveData<List<Shows>>) : androidx.recyclerview.widget.RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_show, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount() : Int {
        shows.value.apply {
            return if (this == null || this.isEmpty())
                0
            else
                this.size
        }
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        shows.value?.let {
            val show = it[position].show
            holder.bind(show)
        }
    }

}
