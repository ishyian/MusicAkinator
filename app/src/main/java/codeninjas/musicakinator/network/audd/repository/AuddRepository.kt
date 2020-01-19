package codeninjas.musicakinator.network.audd.repository

import codeninjas.musicakinator.data.audd.responseModel.GetSongsByLyricResponseModel
import codeninjas.musicakinator.network.provider.AuddGlobalDataProvider
import codeninjas.musicakinator.util.constants.ApiConstants
import io.reactivex.Observable
import javax.inject.Inject


class AuddRepository
@Inject
constructor(
    private val api: AuddGlobalDataProvider
) {

    fun getSongsByLyrics(token: String = ApiConstants.AUDD_API_TOKEN, lyricQuery: String): Observable<GetSongsByLyricResponseModel>{
        return api.getSongsByLyric(token, lyricQuery)
    }


}
