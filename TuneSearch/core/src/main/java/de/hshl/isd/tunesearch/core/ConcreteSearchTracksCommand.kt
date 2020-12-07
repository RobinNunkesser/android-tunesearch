package de.hshl.isd.tunesearch.core

import de.hshl.isd.explicitarchitecture.tunesearch.core.MockSearchTracksCommand
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConcreteSearchTracksCommand(private val tunesSearchEngine: TunesSearchEngine) :
    SearchTracksCommand {

    override fun execute(
        inDTO: SearchTracksDTO,
        successHandler: (success: List<CollectionEntity>) -> Unit,
        errorHandler: (error: Throwable) -> Unit
    ) {
        val scope = MainScope()
        scope.launch {
            withContext(Dispatchers.IO) {
                val result = tunesSearchEngine.getSongs(inDTO.term)
                withContext(Dispatchers.Main) {
                    //result.onSuccess { successHandler(mapTracks(it)) }
                    //result.onFailure { errorHandler(it) }
                    successHandler(MockSearchTracksCommand().mock)
                }
            }
        }
    }

    private fun mapTracks(tracks: List<TrackEntity>): List<CollectionEntity> {
        val collections = tracks.sorted().groupBy { it.collectionName }
        return collections.keys.map { CollectionEntity(it, collections.getValue(it)) }
    }
}