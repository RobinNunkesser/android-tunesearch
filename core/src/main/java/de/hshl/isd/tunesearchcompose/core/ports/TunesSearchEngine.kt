package de.hshl.isd.tunesearchcompose.core.ports

import io.github.robinnunkesser.explicitarchitecture.tunesearch.core.ports.TrackEntity

interface TunesSearchEngine {
    suspend fun getSongs(term: String): List<TrackEntity>
}