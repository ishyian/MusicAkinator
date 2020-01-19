package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.other.base.BaseView

interface GameView : BaseView {

    fun onNoSongResultsFound()
    fun onSongFound(song: DizzerTrackResponseModel)
}