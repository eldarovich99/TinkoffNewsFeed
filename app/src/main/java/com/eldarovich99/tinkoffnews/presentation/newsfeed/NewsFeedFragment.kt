package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.tinkoffnews.R
import kotlinx.android.synthetic.main.news_feed_fragment.*

class NewsFeedFragment: Fragment() {
    private val viewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }
    companion object {
        fun newInstance() : NewsFeedFragment{
            return NewsFeedFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.news_feed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        news_feed_recycler.adapter = NewsFeedAdapter(context!!)

        super.onViewCreated(view, savedInstanceState)
    }
}