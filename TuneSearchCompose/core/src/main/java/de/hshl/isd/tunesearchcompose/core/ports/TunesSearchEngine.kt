package de.hshl.isd.tunesearchcompose.core.ports

import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.TrackEntity

interface TunesSearchEngine {
    suspend fun getSongs(term: String): List<TrackEntity>
}