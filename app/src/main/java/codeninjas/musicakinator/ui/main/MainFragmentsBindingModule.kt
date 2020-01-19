package codeninjas.musicakinator.ui.main



import codeninjas.musicakinator.other.custom.annotations.PerFragment
import codeninjas.musicakinator.ui.main.game.GameFragment
import codeninjas.musicakinator.ui.main.search.SearchTrackFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindSearchTrackFragment(): SearchTrackFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindGameFragment(): GameFragment
}