package codeninjas.musicakinator.di.modules

import codeninjas.musicakinator.other.custom.annotations.GlobalNavigatorHolderQualifier
import codeninjas.musicakinator.other.custom.annotations.GlobalRouterQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun provideCicerone(): Cicerone<Router> {
        return cicerone
    }

    @Singleton
    @GlobalRouterQualifier
    @Provides
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Singleton
    @GlobalNavigatorHolderQualifier
    @Provides
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}