package codeninjas.musicakinator.di.modules

import codeninjas.musicakinator.other.custom.annotations.PerActivity
import codeninjas.musicakinator.ui.main.MainActivity
import codeninjas.musicakinator.ui.main.MainFragmentsBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [MainFragmentsBindingModule::class]
    )
    abstract fun bindMainActivity(): MainActivity


}