package codeninjas.musicakinator.network.audd.usecase

import codeninjas.musicakinator.network.audd.repository.AuddRepository
import codeninjas.musicakinator.util.base.BaseUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetSongByLyricUseCase
@Inject
constructor(repository: AuddRepository) :
    BaseUseCase<AuddRepository, String, Observable<String>>(repository) {
    override fun createObservable(arg: String?): Observable<String> {
        return repository.getSongsByLyrics(lyricQuery = arg!!)
            .map {
                val songs = it.result.map { song -> song.title }.toList()
                songs.firstOrNull()
            }
    }
}