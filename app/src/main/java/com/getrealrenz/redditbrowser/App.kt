package com.getrealrenz.redditbrowser

import android.app.Activity
import android.app.Application
import com.getrealrenz.redditbrowser.data.injection.NetworkModule
import com.getrealrenz.redditbrowser.data.injection.RepositoryModule
import com.getrealrenz.redditbrowser.di.app.AppModule
import com.getrealrenz.redditbrowser.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**
 * Created on 02.10.2018.
 */
class App : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .repositoryModule(RepositoryModule())
                .create(this)
                .inject(this)

    }

    override fun activityInjector() = androidInjector
}