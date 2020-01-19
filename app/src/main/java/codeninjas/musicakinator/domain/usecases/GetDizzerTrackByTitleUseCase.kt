package codeninjas.musicakinator.domain.usecases

import codeninjas.musicakinator.domain.models.responseModels.DizzerTrackResponseModel
import codeninjas.musicakinator.domain.repositories.DizzerRepository
import codeninjas.musicakinator.other.base.BaseUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetDizzerTrackByTitleUseCase
@Inject
constructor(
    repository: DizzerRepository
) : BaseUseCase<DizzerRepository, String, Observable<DizzerTrackResponseModel>>(repository) {
    override fun createObservable(arg: String?): Observable<DizzerTrackResponseModel> {
        return repository.getTrackByTitle(title = arg!!)
    }
}