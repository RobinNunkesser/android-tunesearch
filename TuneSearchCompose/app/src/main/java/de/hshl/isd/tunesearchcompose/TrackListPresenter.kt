package de.hshl.isd.tunesearchcompose

import de.hshl.isd.basiccleanarch.Presenter

class TrackListPresenter : Presenter<List<TrackEntity>, Map<String, List<TrackViewModel>>> {
    override fun present(model: List<TrackEntity>): Map<String, List<TrackViewModel>> {
        val trackPresenter = TrackPresenter()
        val tracks = model.sorted()
        val collections = tracks.groupBy { it.collectionName }
        return collections.mapValues {
                collection -> collection.value.map {
                track -> trackPresenter.present(track)
        }
        }
    }
}