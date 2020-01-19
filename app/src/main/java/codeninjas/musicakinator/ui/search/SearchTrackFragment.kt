package codeninjas.musicakinator.ui.search


import android.os.Bundle
import android.view.View
import codeninjas.musicakinator.R
import codeninjas.musicakinator.ui.base.BaseFragment
import codeninjas.musicakinator.util.annotations.LayoutResourceId
import codeninjas.musicakinator.util.extensions.setRoundedBtnBackground
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_search_track.*
import javax.inject.Inject

@LayoutResourceId(R.layout.fragment_search_track)
class SearchTrackFragment : BaseFragment(),
    SearchTrackView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchTrackPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun renderView(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() {
        btn_guess.setRoundedBtnBackground(7, R.color.colorPrimary)
        btn_guess.setOnClickListener {
            val lyrics = edt_input_lyrics.text.toString()
            presenter.getTrackByLyrics(lyrics)
        }
    }
}
