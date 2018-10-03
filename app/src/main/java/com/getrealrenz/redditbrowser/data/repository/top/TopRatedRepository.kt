package com.getrealrenz.redditbrowser.data.repository.top

import com.getrealrenz.redditbrowser.data.entity.PostData
import io.reactivex.Single

interface TopRatedRepository {
    fun getTopRated(limit: Int, count: Int): Single<List<PostData?>>
}