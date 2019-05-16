package com.eldarovich99.tinkoffnews.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.eldarovich99.tinkoffnews.R
import com.eldarovich99.tinkoffnews.presentation.newsfeed.NewsFeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, NewsFeedFragment.newInstance())
            .commit()
    }
}
