package com.getrealrenz.redditbrowser.presentation.top

import com.getrealrenz.redditbrowser.di.fragment.FragmentScope
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created on 02.10.2018.
 */
@FragmentScope
@Subcomponent(modules = arrayOf(TopRatedComponent.TopRatedModule::class))
interface TopRatedComponent : AndroidInjector<TopRatedFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TopRatedFragment>() {

    }

    @Module
    class TopRatedModule {
        @Provides
        fun providePresenter(presenter: TopRatedPresenter): TopRatedContract.Presenter = presenter
    }
}