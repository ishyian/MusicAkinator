package codeninjas.musicakinator.ui.main.search


import android.os.Bundle
import android.view.View
import codeninjas.musicakinator.R
import codeninjas.musicakinator.other.base.BaseFragment
import codeninjas.musicakinator.other.custom.annotations.LayoutResourceId
import codeninjas.musicakinator.other.custom.extensions.setInputBackground
import codeninjas.musicakinator.other.custom.extensions.setRoundedBtnBackground
import codeninjas.musicakinator.other.custom.extensions.showAlertMessage
import codeninjas.musicakinator.other.screens.MainScreens
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_search_track.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@LayoutResourceId(R.layout.fragment_search_track)
class SearchTrackFragment : BaseFragment(),
    SearchTrackView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchTrackPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
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
    }

    override fun onSuccessGetSongs(songs: List<String>) {
        router.navigateTo(MainScreens.GameScreen(songs))
    }

    override fun onGetSongsIsEmpty() {
        showAlertMessage("songs is empty")
    }

}
