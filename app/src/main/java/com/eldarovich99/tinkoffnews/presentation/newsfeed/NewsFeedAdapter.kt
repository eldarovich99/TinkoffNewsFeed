package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.data.db.entity.News

class NewsFeedAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var news = emptyList<News>() // Cached copy of news

    inner class NewsFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.title_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val itemView = inflater.inflate(R.layout.news_item, parent, false)
        return NewsFeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        val current = news[position]
        holder.wordItemView.text = current.payload.title.text
    }

    internal fun setNews(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun getItemCount() = news.size

    fun getTaskAtPosition(position: Int): News {
        return news[position]
    }
}