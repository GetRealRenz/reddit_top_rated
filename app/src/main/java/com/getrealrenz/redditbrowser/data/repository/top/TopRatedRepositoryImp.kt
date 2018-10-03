package com.getrealrenz.redditbrowser.data.repository.top

import com.getrealrenz.redditbrowser.applyIOSchedulers
import com.getrealrenz.redditbrowser.data.entity.PostData
import com.getrealrenz.redditbrowser.data.repository.top.storage.TopRatedStorageRemote
import io.reactivex.Single
import javax.inject.Inject

class TopRatedRepositoryImp @Inject constructor(private var topRatedStorageRemote: TopRatedStorageRemote) : TopRatedRepository {
    override fun getTopRated(limit: Int, count: Int): Single<List<PostData?>> = topRatedStorageRemote.getTopRated(limit, count)!!.applyIOSchedulers()
}