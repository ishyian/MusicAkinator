package codeninjas.musicakinator.ui.search

import codeninjas.musicakinator.network.audd.usecase.GetSongsByLyricUseCase
import codeninjas.musicakinator.ui.base.BasePresenter
import codeninjas.musicakinator.util.annotations.PerFragment
import codeninjas.musicakinator.util.extensions.async
import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@PerFragment
@InjectViewState
class SearchTrackPresenter
@Inject
constructor(
    private val router: Router,
    private val getSongsByLyricUseCase: GetSongsByLyricUseCase
) : BasePresenter<SearchTrackView>() {

    fun getSongByLyrics(lyrics: String) {
        if (lyrics.isEmpty()) return
        getSongsByLyricUseCase.createObservable(lyrics)
            .async()
            .doOnSubscribe { viewState.showProgress() }
            .doOnTerminate { viewState.hideProgress() }
            .subscribe({
                val songs = it.result.map { song -> song.title }.toList()
                viewState.showMessage(songs.joinToString(",\n"))
            }, {
                viewState.showError(it)
            }
            ).tracked()
    }

}
