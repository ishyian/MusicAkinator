package codeninjas.musicakinator.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import codeninjas.musicakinator.R
import codeninjas.musicakinator.other.base.BaseActivity
import codeninjas.musicakinator.other.base.OnSpeechRecognizeListener
import codeninjas.musicakinator.other.custom.annotations.GlobalNavigatorHolderQualifier
import codeninjas.musicakinator.other.custom.annotations.GlobalRouterQualifier
import codeninjas.musicakinator.other.custom.extensions.currentFragment
import codeninjas.musicakinator.other.screens.MainScreens
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val navigator = SupportAppNavigator(this, R.id.container)

    @Inject
    @GlobalNavigatorHolderQualifier
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @GlobalRouterQualifier
    lateinit var router: Router


    override fun renderView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            router.newRootScreen(MainScreens.SearchTrackScreen())
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_RECOGNIZER && resultCode == Activity.RESULT_OK) {
            data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let {
                val currentFragment = supportFragmentManager.currentFragment
                if (currentFragment is OnSpeechRecognizeListener) {
                    currentFragment.onSpeechRecognize(it)
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_SPEECH_RECOGNIZER = 600
    }

}
