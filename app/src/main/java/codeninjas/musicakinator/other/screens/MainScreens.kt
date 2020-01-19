package codeninjas.musicakinator.other.screens

import androidx.fragment.app.Fragment
import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.ui.main.game.GameFragment
import codeninjas.musicakinator.ui.main.search.SearchTrackFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainScreens {

    class SearchTrackScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchTrackFragment()
        }
    }

    class GameScreen(private val songs: List<String>) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return GameFragment.newInstance(SongListDataModel(
                songs = songs
            ))
        }
    }
}