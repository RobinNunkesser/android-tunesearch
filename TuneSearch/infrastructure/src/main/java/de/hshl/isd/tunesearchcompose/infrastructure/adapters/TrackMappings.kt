package de.hshl.isd.tunesearchcompose.infrastructure.adapters

import de.hshl.isd.tunesearchcompose.infrastructure.ITunesTrack
import io.github.robinnunkesser.explicitarchitecture.tunesearch.core.ports.TrackEntity

fun ITunesTrack.toTrackEntity(): TrackEntity {
    return TrackEntity(this.artistName, this.collectionName, this.trackName, this.trackNumber, this.discNumber, this.artworkUrl100)
}