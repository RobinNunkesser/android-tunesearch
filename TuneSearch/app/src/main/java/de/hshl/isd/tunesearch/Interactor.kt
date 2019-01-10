package de.hshl.isd.tunesearch

import android.util.Log
import de.hshl.isd.tunesearch.common.InputBoundary
import de.hshl.isd.tunesearch.common.OutputBoundary
import de.hshl.isd.tunesearch.common.Response
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class Interactor : InputBoundary<SearchRequest> {
    override fun send(request: SearchRequest, outputBoundary: OutputBoundary) {
       GlobalScope.async {
           val response = ITunesSearchGateway().search(request.term)
           when (response) {
               is Response.Success<*> -> {
                   val tracks = (response.value as List<TrackEntity>).sorted()
                   val collections = tracks.groupBy { it.collectionName }
                   val trackList: MutableList<ItemViewModel> = mutableListOf()
                   for (collection in collections.keys) {
                       trackList.add(ItemViewModel(collection))
                       for (track in collections[collection]!!) {
                           trackList.add(TrackPresenter().present(track))
                       }
                   }
                   outputBoundary.receive(Response.Success(trackList))
               }
               is Response.Failure -> {
                   outputBoundary.receive(response)
               }
           }
       }
    }
}