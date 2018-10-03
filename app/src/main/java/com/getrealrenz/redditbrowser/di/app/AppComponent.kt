package com.getrealrenz.redditbrowser.di.app

import com.getrealrenz.redditbrowser.App
import com.getrealrenz.redditbrowser.data.injection.NetworkModule
import com.getrealrenz.redditbrowser.data.injection.RepositoryModule
import com.getrealrenz.redditbrowser.data.injection.ServiceModule
import com.getrealrenz.redditbrowser.di.activity.ActivityBindingsModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ActivityBindingsModule::class,
        AndroidSupportInjectionModule::class))
public interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        abstract fun appModule(appModule: AppModule): Builder
        abstract fun networkModule(networkModule: NetworkModule): Builder
        abstract fun repositoryModule(repositoryModule: RepositoryModule):Builder
    }
}