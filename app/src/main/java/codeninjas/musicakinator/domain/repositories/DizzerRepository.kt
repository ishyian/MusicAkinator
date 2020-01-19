package codeninjas.musicakinator.domain.repositories

import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.domain.providers.DizzerGlobalDataProvider
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