package codeninjas.musicakinator.di.modules



import codeninjas.musicakinator.ui.main.MainActivity
import codeninjas.musicakinator.ui.main.module.MainFragmentsBindingModule
import codeninjas.musicakinator.util.annotations.PerActivity
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