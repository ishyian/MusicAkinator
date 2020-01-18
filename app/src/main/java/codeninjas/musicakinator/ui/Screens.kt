package codeninjas.musicakinator.ui

import androidx.fragment.app.Fragment
import codeninjas.musicakinator.ui.search.SearchTrackFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class SearchTrackScreen: SupportAppScreen(){
        override fun getFragment(): Fragment {
            return SearchTrackFragment()
        }
    }

}