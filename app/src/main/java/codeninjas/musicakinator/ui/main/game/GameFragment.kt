package codeninjas.musicakinator.ui.main.game


import android.os.Bundle
import android.view.View
import codeninjas.musicakinator.R
import codeninjas.musicakinator.other.base.BaseFragment
import codeninjas.musicakinator.other.custom.annotations.LayoutResourceId
import codeninjas.musicakinator.other.custom.constants.SONG_LIST_JSON_EXTRA
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.gson.Gson
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

    }

    companion object {
        fun newInstance(songs: List<String>): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    val json = Gson().toJson(songs)
                    putString(SONG_LIST_JSON_EXTRA, json)
                }
            }
        }
    }
}
