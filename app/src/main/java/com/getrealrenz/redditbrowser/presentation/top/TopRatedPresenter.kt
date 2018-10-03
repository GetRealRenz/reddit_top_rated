package com.getrealrenz.redditbrowser.presentation.top

import android.annotation.SuppressLint
import com.getrealrenz.redditbrowser.data.net.RetrofitException
import com.getrealrenz.redditbrowser.domain.top.TopRatedInteractor
import com.getrealrenz.redditbrowser.presentation.Constants
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable


/**
 * Created on 02.10.2018.
 */

class TopRatedPresenter @Inject constructor(var topRatedInteractor: TopRatedInteractor,
                                            var router: TopRatedContract.Router) : TopRatedContract.Presenter {

    var view: TopRatedContract.View? = null
    var nextPageId = ""
    private val paginator = PublishProcessor.create<Int>()
    private val compositeDisposable = CompositeDisposable()
    override fun attachView(view: TopRatedContract.View) {
        this.view = view
        this.view?.setPaginator(paginator)

    }

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    @SuppressLint("CheckResult")
    override fun getTopRated() {
        val disposable = paginator
                .onBackpressureDrop()
                .concatMapSingle { topRatedInteractor.getTopRated(Constants.DEFAULT_LIMIT, nextPageId) }
                .subscribe({ it ->
                    view?.showTopRated(it)
                    nextPageId = it.last()?.name!!
                }, { t -> dispatchRequestError(t) })
        paginator.onNext(0)
        compositeDisposable.addAll(disposable)
    }

    override fun onPostClicked(url: String) {
        router.openPost(url)
    }

    private fun dispatchRequestError(error: Throwable) {
        if (error is RetrofitException) {
            view?.showError(error.message!!)
        } else {
            view?.showError(error.localizedMessage)
        }
    }


}