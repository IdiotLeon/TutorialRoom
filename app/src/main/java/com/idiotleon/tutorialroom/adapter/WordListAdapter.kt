package com.idiotleon.tutorialroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idiotleon.tutorialroom.R
import com.idiotleon.tutorialroom.model.Word

class WordListAdapter(context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.tv_rv_item)
    }

    private val inflator: LayoutInflater = LayoutInflater.from(context)
    private var words: List<Word>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
        WordViewHolder(inflator.inflate(R.layout.rv_item, parent, false))

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.wordItemView.text = this.words?.get(position)?.toString() ?: "No Word"
    }

    fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = this.words?.size ?: 0
}