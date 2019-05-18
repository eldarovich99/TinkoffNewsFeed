package com.eldarovich99.tinkoffnews.presentation.newsfeed

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.tinkoffnews.Injector
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.di.factories.ViewModelFactory
import com.eldarovich99.tinkoffnews.presentation.overviewNews.OverviewNewsFragment
import kotlinx.android.synthetic.main.news_feed_fragment.*
import javax.inject.Inject


class NewsFeedFragment: Fragment() {
    @Inject
    lateinit var viewModel: NewsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var adapter: NewsFeedAdapter
    companion object {
        fun newInstance() : NewsFeedFragment{
            return NewsFeedFragment()
        }

        const val BUNDLE_KEY  = "bundle"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_feed_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        init()
        super.onCreate(savedInstanceState)
    }

    private fun init() {
        Injector.getAppComponent().inject(this)
        ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        retainInstance = true
        adapter = NewsFeedAdapter(context!!, object : IOpenFragmentListener{
            override fun openFragment(news: News) {
                activity!!.supportFragmentManager!!
                    .beginTransaction()
                    .replace(R.id.fragment_container, OverviewNewsFragment.newInstance(news))
                    .addToBackStack(null)
                    .commit()
            }
        })
        viewModel.allNews.observe(this, Observer {news->
            news?.let {
                adapter.setNews(news)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        news_feed_recycler.adapter = adapter
        swipe_refresh_layout.setOnRefreshListener {
            viewModel.getNewsList()
            //swipe_refresh_layout.isRefreshing = false
        }

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        news_feed_recycler.adapter = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        activity?.finish()
        super.onDestroy()
    }
}

interface IOpenFragmentListener{
    fun openFragment(news: News)
}