package codeninjas.musicakinator.network.audd.repository

import codeninjas.musicakinator.data.audd.responseModel.DizzerTrackResponseModel
import codeninjas.musicakinator.network.provider.DizzerGlobalDataProvider
import io.reactivex.Observable
import javax.inject.Inject

class DizzerRepository
@Inject
constructor(
    private val api: DizzerGlobalDataProvider
) {
    fun getTrackByTitle(title: String): Observable<DizzerTrackResponseModel> {
        return api.getTracks(title)
            .map {
                it.data?.firstOrNull()
            }
    }
}