package com.eldarovich99.tinkoffnews.presentation.Newsfeed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.tinkoffnews.R

class NewsFeedFragment: Fragment() {
    companion object {
        fun newInstance() : NewsFeedFragment{
            return NewsFeedFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_feed_fragment, container, false)
    }
}