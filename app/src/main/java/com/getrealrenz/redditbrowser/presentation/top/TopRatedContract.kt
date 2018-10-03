package com.getrealrenz.redditbrowser.presentation.top

import com.getrealrenz.redditbrowser.data.entity.PostData
import com.getrealrenz.redditbrowser.presentation.base.BasePresenter

/**
 * Created on 02.10.2018.
 */
interface TopRatedContract {
    interface View {
        fun showTopRated(topRated: List<PostData?>)
    }

    interface Presenter : BasePresenter<View> {
        fun getTopRated()
    }

    interface EventListener {

    }

    interface Router {
    }
}