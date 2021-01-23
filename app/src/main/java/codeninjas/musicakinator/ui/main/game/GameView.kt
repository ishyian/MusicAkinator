package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.other.base.BaseView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface GameView : BaseView {

    @StateStrategyType(SkipStrategy::class)
    fun onNoSongResultsFound()

    @StateStrategyType(SkipStrategy::class)
    fun onSongFound(song: DizzerTrackResponseModel)

    @StateStrategyType(SkipStrategy::class)
    fun showResultDialog(text: String)
}