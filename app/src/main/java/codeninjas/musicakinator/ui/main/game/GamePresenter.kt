package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.usecases.GetDizzerTrackByTitleUseCase
import codeninjas.musicakinator.other.base.BasePresenter
import codeninjas.musicakinator.other.custom.annotations.PerFragment
import codeninjas.musicakinator.other.custom.extensions.async
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

@PerFragment
@InjectViewState
class GamePresenter
@Inject
constructor(
    private val getDizzerTrackByTitleUseCase: GetDizzerTrackByTitleUseCase
) : BasePresenter<GameView>() {

    private var tryCount = 0
    private lateinit var songs: ArrayList<String>

    fun loadSongs(songsExtra: ArrayList<String>?) {
        tryCount = songsExtra!!.size
        songs = songsExtra
        nextSong()
    }

    fun nextSong() {
        if (tryCount > 0) {
            tryCount--
            getSongAudio(songs.first())
            songs.removeAt(0)
        } else viewState.onNoSongResultsFound()
    }

    private fun getSongAudio(title: String){
        getDizzerTrackByTitleUseCase.createObservable(title)
            .async()
            .doOnSubscribe { viewState.showProgress() }
            .doOnTerminate { viewState.hideProgress() }
            .subscribe({
                viewState.onSongFound(it.title)
            }, {
                viewState.showError(it)
            }).tracked()

    }

    fun navigateToResult() {

    }

}
