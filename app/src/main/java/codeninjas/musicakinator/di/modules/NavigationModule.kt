package codeninjas.musicakinator.di.modules

import dagger.Module
import dagger.Provides

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> {
        return cicerone
    }

    @Singleton
    @Provides
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Singleton
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

}