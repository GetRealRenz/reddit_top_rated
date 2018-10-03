package com.getrealrenz.redditbrowser.presentation.top

import com.getrealrenz.redditbrowser.data.entity.PostData
import com.getrealrenz.redditbrowser.presentation.base.BasePresenter
import io.reactivex.processors.PublishProcessor

/**
 * Created on 02.10.2018.
 */
interface TopRatedContract {
    interface View {
        fun showTopRated(topRated: List<PostData?>)
        fun setPaginator(paginator: PublishProcessor<Int>)
        fun showError(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getTopRated()
        fun onPostClicked(url: String)
    }

    interface Router {
        fun openPost(url: String)
    }
}