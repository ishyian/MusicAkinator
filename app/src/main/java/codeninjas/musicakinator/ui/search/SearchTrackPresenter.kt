package codeninjas.musicakinator.ui.search

import codeninjas.musicakinator.ui.base.BasePresenter
import codeninjas.musicakinator.util.annotations.PerFragment
import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@PerFragment
@InjectViewState
class SearchTrackPresenter
@Inject
constructor(private val router: Router) : BasePresenter<SearchTrackView>(){

}
