package de.hshl.isd.tunesearchcompose.core

import de.hshl.isd.tunesearchcompose.core.ports.TunesSearchEngine
import io.github.robinnunkesser.explicitarchitecture.tunesearch.core.ports.CollectionEntity
import io.github.robinnunkesser.explicitarchitecture.tunesearch.core.ports.SearchTracksCommand
import io.github.robinnunkesser.explicitarchitecture.tunesearch.core.ports.SearchTracksDTO
import kotlinx.coroutines.*

class ConcreteSearchTracksCommand(val scope: CoroutineScope, val service: TunesSearchEngine) :
    SearchTracksCommand {
    override fun execute(
        inDTO: SearchTracksDTO,
        successHandler: (success: List<CollectionEntity>) -> Unit,
        errorHandler: (error: Throwable) -> Unit
    ) {
        scope.launch {
            withContext(Dispatchers.IO) {
                val result = kotlin.runCatching {
                    service.getSongs(inDTO.term)
                }
                result.onSuccess { tracks ->
                    val groupedTracks =
                        tracks.sorted().groupBy { it.collectionName }
                    var collections = mutableListOf<CollectionEntity>()
                    for ((name, tracks) in groupedTracks) {
                        collections.add(CollectionEntity(name, tracks))
                    }
                    withContext(Dispatchers.Main) {
                        successHandler(collections)
                    }
                }
                result.onFailure(errorHandler)
            }
        }
    }
}