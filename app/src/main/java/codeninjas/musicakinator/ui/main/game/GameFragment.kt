package codeninjas.musicakinator.ui.main.game

import android.os.Bundle
import android.view.View
import codeninjas.musicakinator.R
import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.other.base.BaseFragment
import codeninjas.musicakinator.other.custom.annotations.LayoutResourceId
import codeninjas.musicakinator.other.custom.constants.SONG_LIST_DATA_MODEL_EXTRA
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.google.gson.Gson
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
        btnNo.setOnClickListener {
            presenter.nextSong()
        }
        btnYes.setOnClickListener {
            presenter.navigateToResult(songFound = true)
        }
    }

    override fun onNoSongResultsFound() {
        tvSongTitle.text = "No results found"
        ivSongImage.setImageDrawable(null)
        presenter.navigateToResult(songFound = false)
    }

    override fun onSongFound(song: DizzerTrackResponseModel) {
        song.apply {
            tvSongTitle.text = song.title
            Glide.with(context!!).load(album.cover).into(ivSongImage)
        }
    }

    companion object {
        fun newInstance(songListDataModel: SongListDataModel): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    val json = Gson().toJson(songListDataModel)
                    putString(SONG_LIST_DATA_MODEL_EXTRA, json)
                }
            }
        }
    }
}
