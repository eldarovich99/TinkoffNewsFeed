package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.data.db.entity.NewsTitle
import java.text.SimpleDateFormat
import java.util.*

class NewsFeedAdapter internal constructor(val listener: IOpenFragmentListener
) : RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>() {
    private var news = emptyList<NewsTitle>()

    class NewsFeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.title_text_view)
        val dateItemView: TextView = itemView.findViewById(R.id.date_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val holder =  NewsFeedViewHolder(itemView)
        itemView.setOnClickListener {
            listener.openFragment(news[holder.adapterPosition].id)
        }
        return holder
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        val current = news[position]
        holder.wordItemView.text = current.text
        holder.dateItemView.text = SimpleDateFormat("dd.mm.yyyy  hh:mm:ss", Locale("ru"))
            .format(Date(current.publicationDate))
            .toString()
    }

    internal fun setNews(newsTitles: List<NewsTitle>) {
        this.news = newsTitles
        notifyDataSetChanged()
    }

    internal fun updateNews(){

    }

    override fun getItemCount() = news.size

    fun getNewsAtPosition(position: Int): NewsTitle {
        return news[position]
    }
}