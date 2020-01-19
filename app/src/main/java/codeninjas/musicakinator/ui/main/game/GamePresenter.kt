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
    private val songListDataModel: SongListDataModel,
    private val getDizzerTrackByTitleUseCase: GetDizzerTrackByTitleUseCase
) : BasePresenter<GameView>() {

    private var currentSongPosition = 0

    init {
        getNextTrackForListing()
    }

    fun getNextTrackForListing() {
        getDizzerTrackByTitleUseCase.createObservable(songListDataModel.songs[currentSongPosition])
            .async()
            .subscribe()
            .tracked()
    }
}
