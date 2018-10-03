package com.getrealrenz.redditbrowser.data.injection

import com.getrealrenz.redditbrowser.data.repository.top.TopRatedRepository
import com.getrealrenz.redditbrowser.data.repository.top.TopRatedRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTopRatedRepository(topRatedRepositoryImp: TopRatedRepositoryImp): TopRatedRepository = topRatedRepositoryImp
}