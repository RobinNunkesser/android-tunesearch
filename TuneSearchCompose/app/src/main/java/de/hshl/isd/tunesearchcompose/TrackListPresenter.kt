package de.hshl.isd.tunesearchcompose

import de.hshl.isd.basiccleanarch.Presenter

class TrackListPresenter : Presenter<List<TrackEntity>, List<ItemViewModel>> {
    override fun present(model: List<TrackEntity>): List<ItemViewModel> {
        val trackPresenter = TrackPresenter()
        val tracks = model.sorted()
        val collections = tracks.groupBy { it.collectionName }
        val trackList: MutableList<ItemViewModel> = mutableListOf()
        for (collection in collections.keys) {
            trackList.add(ItemViewModel(collection))
            for (track in collections[collection]!!) {
                trackList.add(trackPresenter.present(track))
            }
        }
        return trackList

    }
}