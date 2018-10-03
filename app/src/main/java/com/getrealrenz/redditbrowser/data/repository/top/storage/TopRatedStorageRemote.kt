package com.getrealrenz.redditbrowser.data.repository.top.storage

import com.getrealrenz.redditbrowser.data.entity.PostData
import com.getrealrenz.redditbrowser.data.net.RetrofitException
import com.getrealrenz.redditbrowser.data.net.response.TopRatedResponse
import com.getrealrenz.redditbrowser.data.net.service.RedditService
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class TopRatedStorageRemote @Inject constructor(var redditService: RedditService) {
    fun getTopRated(limit: Int, after: String): Single<List<PostData?>>? {
        return redditService.getTopRated(limit, after)
                .flatMap { t: Response<TopRatedResponse> ->
                    if (t.isSuccessful) {
                        Single.just(t.body()!!.data!!.children!!.map { it.data })
                    } else {
                        Single.error(RetrofitException.asRetrofitException(HttpException(t)))
                    }
                }
    }
}