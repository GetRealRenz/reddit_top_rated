package com.getrealrenz.redditbrowser.presentation

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.getrealrenz.redditbrowser.R
import com.getrealrenz.redditbrowser.presentation.top.TopRatedContract
import javax.inject.Inject
import com.getrealrenz.redditbrowser.R.id.mainContainer


/**
 * Created on 03.10.2018.
 */
class MainRouter @Inject constructor(private var appCompatActivity: AppCompatActivity) : TopRatedContract.Router {
    override fun openPost(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(appCompatActivity, Uri.parse(url))
    }

    fun replaceWithoutBackFragment(fragment: Fragment) {
        appCompatActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainContainer, fragment, fragment::class.java.simpleName)
                .commit()
    }

}