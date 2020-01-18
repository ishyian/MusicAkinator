package codeninjas.musicakinator.di

import android.app.Application
import codeninjas.musicakinator.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NavigationModule::class,
        ActivitiesBindingModule::class,
        AppModule::class,
        NetworkModule::class,
        StorageModule::class,
        AuddModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}