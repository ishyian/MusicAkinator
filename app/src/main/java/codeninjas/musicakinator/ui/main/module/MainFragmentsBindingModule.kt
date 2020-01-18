package codeninjas.musicakinator.ui.main.module



import codeninjas.musicakinator.ui.search.SearchTrackFragment
import codeninjas.musicakinator.util.annotations.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindMainScreen(): SearchTrackFragment


}