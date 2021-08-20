package de.hshl.isd.tunesearchcompose.infrastructure.adapters

import de.hshl.isd.tunesearchcompose.core.ports.TunesSearchEngine
import de.hshl.isd.tunesearchcompose.infrastructure.ITunesSearchService
import io.github.robinnunkesser.explicitarchitecture.tunesearch.core.ports.TrackEntity

class TunesSearchEngineAdapter : TunesSearchEngine {

    val service = ITunesSearchService()

    override suspend fun getSongs(term: String): List<TrackEntity> {
        val response = service.search(term).execute()
        return response.body()!!.results.map { it.toTrackEntity() }
    }
}