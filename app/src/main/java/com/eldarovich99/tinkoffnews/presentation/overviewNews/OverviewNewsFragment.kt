package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eldarovich99.tinkoffnews.Injector
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.di.factories.ViewModelFactory
import com.eldarovich99.tinkoffnews.presentation.newsfeed.NewsFeedFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.overview_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class OverviewNewsFragment: Fragment() {
    @Inject
    lateinit var viewModel: OverviewViewModel
    private val compositeDisposable = CompositeDisposable()
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun newInstance(id:String): OverviewNewsFragment {
            val bundle = Bundle()
            bundle.putString(NewsFeedFragment.BUNDLE_KEY, id)
            val fragment = OverviewNewsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true
        super.onCreate(savedInstanceState)
    }

//    private val viewModel:OverviewViewModel by lazy{
//        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
//    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Injector.getAppComponent().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OverviewViewModel::class.java)
        val view = inflater.inflate(R.layout.overview_fragment, container, false)
        val id = arguments?.getString(NewsFeedFragment.BUNDLE_KEY)
        val disposable = viewModel.getContent(id!!).doOnComplete{
            //viewModel.news = arguments?.getParcelable(NewsFeedFragment.BUNDLE_KEY)!!
            val titleRaw = viewModel.news.name.split("-").filter { item -> item.toIntOrNull() == null }.toMutableList()
            titleRaw[0] = titleRaw[0].capitalize()
            view.name_text_view.text = titleRaw.joinToString(" ")
            view.content_text_view.text = viewModel.news.text
            view.id_text_view.text = getString(R.string.id, viewModel.news.id)
            view.date_text_view.text = SimpleDateFormat("dd.mm.yyyy", Locale("ru"))
                .format(Date(viewModel.news.publicationDate.milliseconds))
                .toString()
        }.subscribe()
        compositeDisposable.add(disposable)
        return view
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }
}