package com.getrealrenz.redditbrowser.data.injection

import com.getrealrenz.redditbrowser.data.net.service.RedditService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Singleton
    @Provides
    fun provideRedditService(retrofit: Retrofit) = retrofit.create(RedditService::class.java)
}