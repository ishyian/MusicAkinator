package codeninjas.musicakinator.ui.main.game

import codeninjas.musicakinator.domain.models.dataModels.SongListDataModel
import codeninjas.musicakinator.other.custom.annotations.PerFragment
import codeninjas.musicakinator.other.custom.constants.SONG_LIST_DATA_MODEL_EXTRA
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class GameModule {

    @Provides
    @PerFragment
    fun provideSongListDataModel(fragment: GameFragment, gson: Gson): SongListDataModel {
        val args = fragment.arguments!!
        return gson.fromJson(args.getString(SONG_LIST_DATA_MODEL_EXTRA), SongListDataModel::class.java)
    }
}