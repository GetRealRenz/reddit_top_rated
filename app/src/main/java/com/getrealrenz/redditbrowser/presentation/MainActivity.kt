package com.getrealrenz.redditbrowser.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.getrealrenz.redditbrowser.R
import com.getrealrenz.redditbrowser.presentation.base.BaseActivity
import com.getrealrenz.redditbrowser.presentation.top.TopRatedFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, TopRatedFragment.newInstance())
                .commit()
    }
}
