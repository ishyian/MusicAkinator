package codeninjas.musicakinator.domain.usecases

import codeninjas.musicakinator.domain.repositories.AuddRepository
import codeninjas.musicakinator.other.base.BaseUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetSongsByLyricUseCase
@Inject
constructor(repository: AuddRepository) :
    BaseUseCase<AuddRepository, String, Observable<List<String>>>(repository) {
    override fun createObservable(arg: String?): Observable<List<String>> {
        return repository.getSongsByLyrics(lyricQuery = arg!!)
            .map {
                it.result.map { song -> song.title }.toList()
            }.onErrorReturn {
                emptyList()
            }
    }
}