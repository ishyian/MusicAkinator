package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.other.base.BaseView

interface GameView : BaseView {

    fun onNoSongResultsFound()
    fun onSongFound(title: String)
}