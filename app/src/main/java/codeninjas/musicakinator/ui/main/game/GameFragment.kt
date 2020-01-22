package codeninjas.musicakinator.ui.main.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import codeninjas.musicakinator.R
import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.other.base.BaseFragment
import codeninjas.musicakinator.other.custom.annotations.LayoutResourceId
import codeninjas.musicakinator.other.custom.constants.SONG_LIST_DATA_MODEL_EXTRA
import codeninjas.musicakinator.other.custom.extensions.setRoundedBtnBackground
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.sheet_result_dialog.view.*
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
        btnYes.setRoundedBtnBackground(7, R.color.colorPrimary)
        btnNo.setRoundedBtnBackground(7, R.color.colorError)
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

            //TODO::Play song, maybe ui of player
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
}
