package com.eldarovich99.tinkoffnews.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.presentation.newsfeed.NewsFeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.backStackEntryCount == 0)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFeedFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}
