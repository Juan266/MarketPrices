package com.stock.market.ui.news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stock.market.domain.model.News
import kotlinx.android.synthetic.main.item_news.view.*

class NewsHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var newsMainContainer: ConstraintLayout
    private lateinit var newsTitle: TextView
    private lateinit var newsKeywords: TextView
    private lateinit var newsImage: ImageView


    fun bindItem(news: News, listener: OnNewsClickListener) = with(itemView) {
        newsMainContainer = news_list_item_main_container
        newsTitle = news_list_item_title
        newsKeywords = news_list_item_keywords
        newsImage = news_list_item_image

        newsTitle.text = news.title
        newsKeywords.text = news.description
        Glide.with(context).load(news.imageUrl).thumbnail(0.1f).into(newsImage)

        newsMainContainer.setOnClickListener { listener.openNews(news) }

    }
}