package codeninjas.musicakinator.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {


    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }


}