package codeninjas.musicakinator.ui.main.search

import codeninjas.musicakinator.other.base.BaseView

interface SearchTrackView: BaseView {
    fun onSuccessGetSongs(songs: List<String>)
    fun onGetSongsIsEmpty()
}