package codeninjas.musicakinator.ui.main.search

import codeninjas.musicakinator.domain.usecases.GetSongsByLyricUseCase
import codeninjas.musicakinator.other.base.BasePresenter
import codeninjas.musicakinator.other.custom.annotations.PerFragment
import codeninjas.musicakinator.other.custom.extensions.async
import com.arellomobile.mvp.InjectViewState
import javax.inject.Inject

@PerFragment
@InjectViewState
class SearchTrackPresenter
@Inject
constructor(
    private val getSongsByLyricUseCase: GetSongsByLyricUseCase
) : BasePresenter<SearchTrackView>() {

    fun getSongsByLyrics(lyrics: String) {
        if (lyrics.isEmpty()) return
        getSongsByLyricUseCase.createObservable(lyrics)
            .doOnSubscribe { viewState.showProgress() }
            .doOnTerminate { viewState.hideProgress() }
            .async()
            .subscribe {
                when {
                    it.isEmpty() -> viewState.onGetSongsIsEmpty()
                    else -> viewState.onSuccessGetSongs(it)
                }
            }
            .tracked()
    }
}
