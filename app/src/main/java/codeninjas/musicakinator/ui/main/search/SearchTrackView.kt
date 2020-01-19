package codeninjas.musicakinator.ui.main.search

import codeninjas.musicakinator.other.base.BaseView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SearchTrackView : BaseView {

    @StateStrategyType(SkipStrategy::class)
    fun onSuccessGetSongs(songs: List<String>)

    @StateStrategyType(SkipStrategy::class)
    fun onGetSongsIsEmpty()
}