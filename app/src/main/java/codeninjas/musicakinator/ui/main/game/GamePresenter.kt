package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.domain.usecases.GetDizzerTrackByTitleUseCase
import codeninjas.musicakinator.other.base.BasePresenter
import codeninjas.musicakinator.other.custom.annotations.GlobalRouterQualifier
import codeninjas.musicakinator.other.custom.extensions.async
import codeninjas.musicakinator.other.screens.MainScreens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class GamePresenter
@Inject
constructor(
    private val getDizzerTrackByTitleUseCase: GetDizzerTrackByTitleUseCase,
    @GlobalRouterQualifier private val router: Router
) : BasePresenter<GameView>() {

    private var songs: ArrayList<String> = arrayListOf()
    private var tryCount = if (songs.size > 5) 5 else songs.size
    private var lastFoundSong: String? = null


    fun onCreate(songListDataModel: SongListDataModel) {
        songs = ArrayList(songListDataModel.songs)
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
            viewState.showResultDialog("Ba-boom! We found your song: $lastFoundSong")
        } else viewState.showResultDialog("Sorry, we didn't find your song. You win.")
    }

    fun returnToSearchTrackScreen() {
        router.newRootScreen(MainScreens.SearchTrackScreen())
    }
}
