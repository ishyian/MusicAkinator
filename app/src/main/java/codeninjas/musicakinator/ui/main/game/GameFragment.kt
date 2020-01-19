package codeninjas.musicakinator.ui.main.game


import android.os.Bundle
import android.view.View
import codeninjas.musicakinator.R
import codeninjas.musicakinator.other.base.BaseFragment
import codeninjas.musicakinator.other.custom.annotations.LayoutResourceId
import codeninjas.musicakinator.other.custom.constants.SONG_LIST_EXTRA
import codeninjas.musicakinator.other.custom.extensions.setRoundedBtnBackground
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_game.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@LayoutResourceId(R.layout.fragment_game)
class GameFragment : BaseFragment(), GameView {

    @Inject
    @InjectPresenter
    lateinit var presenter: GamePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    @Inject
    lateinit var router: Router

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        initUi()
        presenter.loadSongs(arguments!!.getStringArrayList(SONG_LIST_EXTRA))
    }

    private fun initUi() {
        btn_yes.setRoundedBtnBackground(7, R.color.colorPrimary)
        btn_no.setRoundedBtnBackground(7, android.R.color.holo_red_dark)
        btn_yes.setOnClickListener {
            presenter.navigateToResult()
        }
        btn_no.setOnClickListener {
            presenter.nextSong()
        }
    }

    override fun onNoSongResultsFound() {
        text_title_song.text = "No results found"
    }

    override fun onSongFound(title: String) {
        text_title_song.setText(title)
    }

    companion object {
        fun newInstance(songs: List<String>): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(SONG_LIST_EXTRA, ArrayList(songs))
                }
            }
        }
    }
}
