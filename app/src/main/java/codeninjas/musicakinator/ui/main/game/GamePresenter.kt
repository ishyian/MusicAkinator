package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.domain.usecases.GetDizzerTrackByTitleUseCase
import codeninjas.musicakinator.other.base.BasePresenter
import codeninjas.musicakinator.other.custom.annotations.PerFragment
import codeninjas.musicakinator.other.custom.extensions.async
import codeninjas.musicakinator.other.screens.MainScreens
import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@PerFragment
@InjectViewState
class GamePresenter
@Inject
constructor(
    songListDataModel: SongListDataModel,
    private val getDizzerTrackByTitleUseCase: GetDizzerTrackByTitleUseCase,
    private val router: Router
) : BasePresenter<GameView>() {

    private val songs: ArrayList<String> = ArrayList(songListDataModel.songs)
    private var tryCount = if (songs.size > 5) 5 else songs.size
    private var lastFoundSong: String? = null

    init {
        nextSong()
    }

    fun nextSong() {
        if (tryCount > 0) {
            tryCount--
            lastFoundSong = songs.first()
            getSongAudio(lastFoundSong!!)
            songs.removeAt(0)
        } else viewState.onNoSongResultsFound()
    }

    private fun getSongAudio(title: String) {
        getDizzerTrackByTitleUseCase.createObservable(title)
            .async()
            .doOnSubscribe { viewState.showProgress() }
            .doOnTerminate { viewState.hideProgress() }
            .subscribe({
                lastFoundSong = "${it.artist.name} - ${it.title}"
                viewState.onSongFound(it)
            }, {
                //If dizzer dont found track
                nextSong()
            }).tracked()

    }

    fun navigateToResult(songFound: Boolean) {
        if (songFound) {
            viewState.showResultDialog("Congratulations! We found your song: $lastFoundSong")
        } else viewState.showResultDialog("Sorry, we didn't find your song. Please be accurate in providing lyrics")
    }

    fun returnToSearchTrackScreen() {
        router.newRootScreen(MainScreens.SearchTrackScreen())
    }
}
