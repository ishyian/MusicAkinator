package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.other.base.BasePresenter
import codeninjas.musicakinator.other.custom.annotations.PerFragment
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

@PerFragment
@InjectViewState
class GamePresenter
@Inject
constructor(
    private val songListDataModel: SongListDataModel
) : BasePresenter<GameView>() {

    init {
        println(songListDataModel)
    }
}
