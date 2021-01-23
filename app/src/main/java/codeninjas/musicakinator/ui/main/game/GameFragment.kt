package codeninjas.musicakinator.ui.main.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import codeninjas.musicakinator.R
import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.other.base.BaseFragment
import codeninjas.musicakinator.other.custom.annotations.GlobalRouterQualifier
import codeninjas.musicakinator.other.custom.constants.SONG_LIST_DATA_MODEL_EXTRA
import codeninjas.musicakinator.other.custom.extensions.setRoundedBtnBackground
import codeninjas.musicakinator.other.custom.extensions.visible
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.sheet_result_dialog.view.*
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class GameFragment : BaseFragment(), GameView {

    override val layoutRes: Int
        get() = R.layout.fragment_game

    @Inject
    lateinit var presenterProvider: Provider<GamePresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    @Inject
    @GlobalRouterQualifier
    lateinit var router: Router

    @Inject
    lateinit var gson: Gson

    lateinit var mediaPlayer: MediaPlayer

    private var isSongPlaying = false

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        btnYes.setRoundedBtnBackground(7, R.color.colorPrimary)
        btnNo.setRoundedBtnBackground(7, R.color.colorError)
        btnNo.setOnClickListener {
            releaseMediaPlayer()
            presenter.nextSong()
        }
        btnYes.setOnClickListener {
            releaseMediaPlayer()
            presenter.navigateToResult(songFound = true)
        }
        btn_control.setOnClickListener {
            isSongPlaying = if (isSongPlaying) {
                pauseSong()
                false
            } else {
                startSong()
                true
            }
        }

        val songs = gson.fromJson(
            requireArguments().getString(SONG_LIST_DATA_MODEL_EXTRA),
            SongListDataModel::class.java
        )
        presenter.onCreate(songs)
    }

    private fun startSong() {
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.start()
            showPauseControl()
        }
    }

    private fun pauseSong() {
        if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            showPlayControl()
        }
    }

    private fun showPauseControl() = btn_control.setImageResource(R.drawable.ic_pause)

    private fun showPlayControl() = btn_control.setImageResource(R.drawable.ic_play_arrow)

    override fun onNoSongResultsFound() {
        tvSongTitle.text = "No results found"
        ivSongImage.setImageDrawable(null)
        presenter.navigateToResult(songFound = false)
    }

    override fun onSongFound(song: DizzerTrackResponseModel) {
        showPauseControl()
        song.apply {
            tvSongTitle.text = "${artist.name} - ${song.title}"
            Glide.with(context!!).load(album.cover).into(ivSongImage)
            createMediaPlayer(preview)
            btn_control.visible()
        }
    }

    private fun createMediaPlayer(url: String) {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(context!!, Uri.parse(url))
        mediaPlayer.prepare()
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        isSongPlaying = true
    }

    private fun releaseMediaPlayer() {
        try {
            if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
                isSongPlaying = false
            }
        } catch (ex: IllegalStateException) {
            ex.printStackTrace()
        }

    }

    override fun showResultDialog(text: String) {
        val bottomSheetDialog = BottomSheetDialog(context!!, R.style.BottomSheetDialog)
        val dialogView = layoutInflater.inflate(R.layout.sheet_result_dialog, null)
        dialogView.dialog_text_title.text = text
        val confirmBtn = dialogView.findViewById<TextView>(R.id.dialog_btn_next)
        bottomSheetDialog.setContentView(dialogView)
        bottomSheetDialog.setCancelable(false)
        confirmBtn.setOnClickListener {
            presenter.returnToSearchTrackScreen()
            bottomSheetDialog.dismiss()

        }
        bottomSheetDialog.apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        releaseMediaPlayer()
    }

}
