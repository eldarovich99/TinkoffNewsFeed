package com.eldarovich99.tinkoffnews.presentation.overviewNews

import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.text.method.LinkMovementMethod
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
        fun newInstance(id:Int): OverviewNewsFragment {
            val bundle = Bundle()
            bundle.putInt(NewsFeedFragment.BUNDLE_KEY, id)
            val fragment = OverviewNewsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.getAppComponent().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OverviewViewModel::class.java)
        retainInstance = true
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.overview_fragment, container, false)
        val id = arguments?.getInt(NewsFeedFragment.BUNDLE_KEY)
        val disposable = viewModel.getContent(id!!)
            .doOnComplete{
            view.title_text_view.text = viewModel.fullNews.title.text
            view.content_text_view.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                Html.fromHtml(viewModel.fullNews.content, Html.FROM_HTML_MODE_COMPACT)
             else
                Html.fromHtml(viewModel.fullNews.content)
            view.content_text_view.movementMethod = LinkMovementMethod.getInstance()
            view.date_text_view.text = SimpleDateFormat("dd.mm.yyyy", Locale("ru"))
                .format(Date(viewModel.fullNews.title.publicationDate))
                .toString()
        }
            .subscribe()
        compositeDisposable.add(disposable)
        return view
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }
}