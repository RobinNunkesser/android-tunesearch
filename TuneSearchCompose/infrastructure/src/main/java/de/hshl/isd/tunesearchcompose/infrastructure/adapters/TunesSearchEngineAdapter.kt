package de.hshl.isd.tunesearchcompose.infrastructure.adapters

import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.TrackEntity
import de.hshl.isd.tunesearchcompose.core.ports.TunesSearchEngine
import de.hshl.isd.tunesearchcompose.infrastructure.ITunesSearchService

class TunesSearchEngineAdapter : TunesSearchEngine {

    val service = ITunesSearchService()

    override suspend fun getSongs(term: String): List<TrackEntity> {
        val response = service.search(term).execute()
        return response.body()!!.results.map { it.toTrackEntity() }
    }
}