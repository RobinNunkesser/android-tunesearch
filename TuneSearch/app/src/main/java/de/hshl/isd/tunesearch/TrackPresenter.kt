package de.hshl.isd.tunesearch

import de.hshl.isd.basiccleanarch.Presenter

class TrackPresenter : Presenter<TrackEntity, TrackViewModel> {
    override fun present(entity: TrackEntity): TrackViewModel {
        return TrackViewModel(entity.artistName, entity.artworkUrl100, "${entity.trackNumber} - ${entity.trackName}")
    }
}