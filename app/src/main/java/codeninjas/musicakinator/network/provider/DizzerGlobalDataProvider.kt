package codeninjas.musicakinator.network.provider

import codeninjas.musicakinator.data.audd.responseModel.DizzerTrackResponseModel
import codeninjas.musicakinator.data.audd.responseModel.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DizzerGlobalDataProvider {

    @GET("/search/track")
    fun getTracks(@Query("q") title: String) : Observable<ResponseModel<List<DizzerTrackResponseModel>>>
}