package de.hshl.isd.tunesearch.infrastructure.adapters

import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.TrackEntity
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.TunesSearchEngine
import de.hshl.isd.tunesearch.infrastructure.ITunesSearchAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TunesSearchEngineAdapter : TunesSearchEngine {

    private lateinit var service: ITunesSearchAPI

    init {
        val retrofit =
            Retrofit.Builder().baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(ITunesSearchAPI::class.java)
    }

    override suspend fun getSongs(term: String): Result<List<TrackEntity>> = try {
        val response = service.search(term).execute()
        val tracks = response.body()!!.results.map {
            TrackEntity(
                it.artistName,
                it.collectionName,
                it.trackName,
                it.trackNumber,
                it.discNumber,
                it.artworkUrl100
            )
        }
        Result.success(tracks)
    } catch (t: Throwable) {
        Result.failure(t)
    }
}