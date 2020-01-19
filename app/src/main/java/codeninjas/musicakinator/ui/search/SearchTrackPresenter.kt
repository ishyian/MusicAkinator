package codeninjas.musicakinator.ui.search

import codeninjas.musicakinator.network.audd.usecase.GetDizzerTrackByTitleUseCase
import codeninjas.musicakinator.network.audd.usecase.GetSongByLyricUseCase
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
    private val getSongByLyricUseCase: GetSongByLyricUseCase,
    private val getDizzerTrackByTitleUseCase: GetDizzerTrackByTitleUseCase
) : BasePresenter<SearchTrackView>() {

    fun getTrackByLyrics(lyrics: String) {
        if (lyrics.isEmpty()) return
        getSongByLyricUseCase.createObservable(lyrics)
            .flatMap { getDizzerTrackByTitleUseCase.createObservable(it) }
            .doOnSubscribe { viewState.showProgress() }
            .doOnTerminate { viewState.hideProgress() }
            .async()
            .subscribe({
                println("item: $it")
            }, {
                println("track not found :(")
            })
            .tracked()
    }
}
