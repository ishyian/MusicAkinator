package codeninjas.musicakinator.network.audd.usecase

import codeninjas.musicakinator.data.audd.responseModel.GetSongsByLyricResponseModel
import codeninjas.musicakinator.network.audd.repository.AuddRepository
import codeninjas.musicakinator.util.base.BaseUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetSongsByLyricUseCase
@Inject
constructor(repository: AuddRepository) :
    BaseUseCase<AuddRepository, String, Observable<GetSongsByLyricResponseModel>>(repository) {


    override fun createObservable(arg: String?): Observable<GetSongsByLyricResponseModel> {
        return repository.getSongsByLyrics(lyricQuery = arg!!)

    }
}