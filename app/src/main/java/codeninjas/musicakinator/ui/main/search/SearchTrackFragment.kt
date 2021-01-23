package codeninjas.musicakinator.ui.main.search


import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import codeninjas.musicakinator.R
import codeninjas.musicakinator.other.base.BaseFragment
import codeninjas.musicakinator.other.base.OnSpeechRecognizeListener
import codeninjas.musicakinator.other.custom.annotations.GlobalRouterQualifier
import codeninjas.musicakinator.other.custom.extensions.setInputBackground
import codeninjas.musicakinator.other.custom.extensions.setRoundedBtnBackground
import codeninjas.musicakinator.other.custom.extensions.showAlertMessage
import codeninjas.musicakinator.other.screens.MainScreens
import codeninjas.musicakinator.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_track.*
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class SearchTrackFragment : BaseFragment(), OnSpeechRecognizeListener,
    SearchTrackView {

    override val layoutRes: Int
        get() = R.layout.fragment_search_track

    @Inject
    lateinit var presenterProvider: Provider<SearchTrackPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    @Inject
    @GlobalRouterQualifier
    lateinit var router: Router

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() {
        edt_input_lyrics.setInputBackground()
        btn_guess.setRoundedBtnBackground(7, R.color.colorPrimary)
        btn_guess.setOnClickListener {
            val lyrics = edt_input_lyrics.text.toString()
            presenter.getSongsByLyrics(lyrics)
        }
        image_mic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en_US")
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            requireActivity().startActivityForResult(
                intent,
                MainActivity.REQUEST_CODE_SPEECH_RECOGNIZER
            )
        }
    }

    override fun onSuccessGetSongs(songs: List<String>) {
        router.navigateTo(MainScreens.GameScreen(songs))
    }

    override fun onInputSongLyricsEmpty() {
        showAlertMessage("Please input lyrics of song!")

    }

    override fun onGetSongsIsEmpty() {
        showAlertMessage("We didn't find any songs. Please be accurate when providing lyrics")
    }

    override fun onSpeechRecognize(speech: List<String>) {
        speech.firstOrNull()?.let {
            edt_input_lyrics.setText(it)
        }
    }
}
