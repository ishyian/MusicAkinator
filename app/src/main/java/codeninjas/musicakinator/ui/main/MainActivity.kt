package codeninjas.musicakinator.ui.main

import android.os.Bundle
import codeninjas.musicakinator.R
import codeninjas.musicakinator.ui.Screens
import codeninjas.musicakinator.ui.base.BaseActivity
import codeninjas.musicakinator.util.annotations.LayoutResourceId
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

@LayoutResourceId(R.layout.activity_main)
class MainActivity : BaseActivity() {

    private val navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder


    @Inject
    lateinit var router: Router


    override fun renderView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            router.newRootScreen(Screens.SearchTrackScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}
