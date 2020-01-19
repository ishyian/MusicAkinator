package codeninjas.musicakinator.domain.providers

import codeninjas.musicakinator.domain.models.responseModels.GetSongsByLyricResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AuddGlobalDataProvider {

    @GET("/findLyrics/")
    fun getSongsByLyric(@Query("api_token") token: String, @Query("q") lyricQuery: String): Observable<GetSongsByLyricResponseModel>
}