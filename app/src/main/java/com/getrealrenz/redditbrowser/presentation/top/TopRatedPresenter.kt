package com.getrealrenz.redditbrowser.presentation.top

/**
 * Created on 02.10.2018.
 */
class TopRatedPresenter : TopRatedContract.Presenter, TopRatedContract.EventListener {
    var view: TopRatedContract.View?=null
    override fun attachView(view: TopRatedContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}