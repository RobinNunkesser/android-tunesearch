package de.hshl.isd.tunesearch

import de.hshl.isd.tunesearch.common.Presenter

class TrackPresenter : Presenter<TrackEntity,TrackViewModel> {
    override fun present(entity: TrackEntity): TrackViewModel {
        return TrackViewModel(entity.artistName, entity.artworkUrl100, "${entity.trackNumber} - ${entity.trackName}")
    }
}