package com.getrealrenz.redditbrowser.presentation.top

import android.annotation.SuppressLint
import com.getrealrenz.redditbrowser.domain.top.TopRatedInteractor
import com.getrealrenz.redditbrowser.presentation.Constants
import javax.inject.Inject

/**
 * Created on 02.10.2018.
 */

class TopRatedPresenter @Inject constructor(var topRatedInteractor: TopRatedInteractor) : TopRatedContract.Presenter, TopRatedContract.EventListener {

    var view: TopRatedContract.View? = null
    var displayedCount = 0
    override fun attachView(view: TopRatedContract.View) {
        this.view = view

    }

    override fun detachView() {
        view = null
    }

    @SuppressLint("CheckResult")
    override fun getTopRated() {
        topRatedInteractor.getTopRated(Constants.DEFAULT_LIMIT, displayedCount)
                ?.subscribe({ it -> view?.showTopRated(it) }, { t -> t.printStackTrace() })
    }

}