package codeninjas.musicakinator.network.audd.usecase

import codeninjas.musicakinator.data.audd.responseModel.DizzerTrackResponseModel
import codeninjas.musicakinator.network.audd.repository.DizzerRepository
import codeninjas.musicakinator.util.base.BaseUseCase
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