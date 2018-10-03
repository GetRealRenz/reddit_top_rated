package com.getrealrenz.redditbrowser.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSharedPrefrences() = context.getSharedPreferences("", Context.MODE_PRIVATE)
}