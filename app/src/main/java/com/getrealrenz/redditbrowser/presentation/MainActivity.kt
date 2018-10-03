package com.getrealrenz.redditbrowser.presentation

import android.os.Bundle
import com.getrealrenz.redditbrowser.R
import com.getrealrenz.redditbrowser.presentation.base.BaseActivity
import com.getrealrenz.redditbrowser.presentation.top.TopRatedFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var mainRouter: MainRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            mainRouter.replaceWithoutBackFragment(TopRatedFragment.newInstance())
        }
    }
}
