package codeninjas.musicakinator.domain.repositories

import codeninjas.musicakinator.domain.models.responseModels.GetSongsByLyricResponseModel
import codeninjas.musicakinator.domain.providers.AuddGlobalDataProvider
import codeninjas.musicakinator.other.custom.constants.ApiConstants
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
