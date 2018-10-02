package com.getrealrenz.redditbrowser.presentation.base

/**
 * Created on 02.10.2018.
 */
interface BasePresenter<V> {
    fun attachView(view: V)

    fun detachView()
}
