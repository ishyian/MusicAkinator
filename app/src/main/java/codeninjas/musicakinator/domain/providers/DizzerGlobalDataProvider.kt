package codeninjas.musicakinator.domain.providers

import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.domain.models.responseModels.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DizzerGlobalDataProvider {

    @GET("/search/track")
    fun getTracks(@Query("q") title: String) : Observable<ResponseModel<List<DizzerTrackResponseModel>>>
}