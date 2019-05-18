package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.data.db.entity.News
import com.eldarovich99.tinkoffnews.presentation.newsfeed.NewsFeedFragment
import kotlinx.android.synthetic.main.overview_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

class OverviewNewsFragment: Fragment() {
    companion object {
        fun newInstance(news: News): OverviewNewsFragment {
            val bundle = Bundle()
            bundle.putParcelable(NewsFeedFragment.BUNDLE_KEY, news)
            val fragment = OverviewNewsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true
        super.onCreate(savedInstanceState)
    }

    private val viewModel:OverviewViewModel by lazy{
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Injector.getAppComponent().inject(this)
        viewModel.news = arguments?.getParcelable(NewsFeedFragment.BUNDLE_KEY)!!
        val view = inflater.inflate(R.layout.overview_fragment, container, false)
        val titleRaw = viewModel.news.name.split("-").filter { item -> item.toIntOrNull() == null }.toMutableList()
        titleRaw[0] = titleRaw[0].capitalize()
        view.name_text_view.text = titleRaw.joinToString(" ")
        view.content_text_view.text = viewModel.news.text
        view.id_text_view.text = getString(R.string.id, viewModel.news.id)
        view.date_text_view.text = SimpleDateFormat("dd.mm.yyyy", Locale("ru"))
            .format(Date(viewModel.news.publicationDate.milliseconds))
            .toString()
        return view
    }
}