package com.getrealrenz.redditbrowser.presentation

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.getrealrenz.redditbrowser.di.activity.ActivityScope
import com.getrealrenz.redditbrowser.presentation.top.TopRatedComponent
import com.getrealrenz.redditbrowser.presentation.top.TopRatedFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.Subcomponent
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap


/**
 * Created on 02.10.2018.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityComponent.MainActivityModule::class,
        MainActivityComponent.FragmentBindingsModule::class))
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

    @Module
    class MainActivityModule {
        @Provides
        fun provideActivit(activity: MainActivity): AppCompatActivity = activity
    }

    @Module(subcomponents = arrayOf(TopRatedComponent::class))
    abstract class FragmentBindingsModule {
        @Binds
        @IntoMap
        @FragmentKey(TopRatedFragment::class)
        abstract fun topRatedComponentBuilder(builder: TopRatedComponent.Builder): AndroidInjector.Factory<out Fragment>
    }


}