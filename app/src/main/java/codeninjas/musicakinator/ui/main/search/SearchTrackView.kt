package codeninjas.musicakinator.ui.main.search

import codeninjas.musicakinator.other.base.BaseView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SearchTrackView : BaseView {

    @StateStrategyType(SkipStrategy::class)
    fun onSuccessGetSongs(songs: List<String>)

    @StateStrategyType(SkipStrategy::class)
    fun onGetSongsIsEmpty()

    @StateStrategyType(SkipStrategy::class)
    fun onInputSongLyricsEmpty()
}