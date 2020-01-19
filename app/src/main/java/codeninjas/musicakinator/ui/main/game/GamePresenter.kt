package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
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
    songListDataModel: SongListDataModel,
    private val getDizzerTrackByTitleUseCase: GetDizzerTrackByTitleUseCase
) : BasePresenter<GameView>() {

    private val songs: ArrayList<String> = ArrayList(songListDataModel.songs)
    private var tryCount = if(songs.size > 5) 5 else songs.size

    init {
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
                viewState.onSongFound(it)
            }, {
                //If dizzer dont found track
                nextSong()
            }).tracked()

    }

    fun navigateToResult(songFound: Boolean) {

    }
}
