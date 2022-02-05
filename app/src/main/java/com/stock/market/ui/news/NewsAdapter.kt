package com.stock.market.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stock.market.R
import com.stock.market.domain.model.News

class NewsAdapter constructor(val listener: OnNewsClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var data: List<News>

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsHolder) {
            val news = data[position]
            holder.bindItem(news, listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (::data.isInitialized) data.size else 0
    }

    fun updateNewsList(newsList: List<News>) {
        data = newsList
        notifyDataSetChanged()
    }
}