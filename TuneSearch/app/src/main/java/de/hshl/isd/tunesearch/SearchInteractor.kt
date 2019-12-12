package de.hshl.isd.tunesearch

import de.hshl.isd.basiccleanarch.Displayer
import de.hshl.isd.basiccleanarch.Presenter
import de.hshl.isd.basiccleanarch.Response
import de.hshl.isd.basiccleanarch.UseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class SearchInteractor(
    override val presenter: Presenter<TrackEntity, TrackViewModel>,
    val gateway: ITunesSearchGateway
) :
    UseCase<SearchRequest, TrackEntity, TrackViewModel> {

    override fun execute(request: SearchRequest, displayer: Displayer) {
       GlobalScope.async {
           val response = gateway.search(request.term)
           when (response) {
               is Response.Success<*> -> {
                   val tracks = (response.value as List<TrackEntity>).sorted()
                   val collections = tracks.groupBy { it.collectionName }
                   val trackList: MutableList<ItemViewModel> = mutableListOf()
                   for (collection in collections.keys) {
                       trackList.add(ItemViewModel(collection))
                       for (track in collections[collection]!!) {
                           trackList.add(presenter.present(track))
                       }
                   }
                   displayer.display(Response.Success(trackList))
               }
               is Response.Failure -> {
                   displayer.display(response)
               }
           }
       }
    }
}