package de.hshl.isd.tunesearch

fun present(entity: TrackEntity) : TrackViewModel {
    return TrackViewModel(entity.artistName, entity.artworkUrl100, "${entity.trackNumber} - ${entity.trackName}")
}