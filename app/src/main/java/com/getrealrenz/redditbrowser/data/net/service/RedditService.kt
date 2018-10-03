package com.getrealrenz.redditbrowser.data.net.service

import com.getrealrenz.redditbrowser.data.net.response.TopRatedResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RedditService {
    @GET("top.json")
    fun getTopRated(@Query("limit") limit: Int,
                    @Query("count") count: Int): Single<Response<TopRatedResponse>>

}
