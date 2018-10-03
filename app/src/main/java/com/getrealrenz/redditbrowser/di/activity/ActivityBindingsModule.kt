package com.getrealrenz.redditbrowser.di.activity

import android.app.Activity
import com.getrealrenz.redditbrowser.presentation.MainActivity
import com.getrealrenz.redditbrowser.presentation.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created on 02.10.2018.
 */
@Module(subcomponents = arrayOf(MainActivityComponent::class))
abstract class ActivityBindingsModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun mainActivityComponentBuilder(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}