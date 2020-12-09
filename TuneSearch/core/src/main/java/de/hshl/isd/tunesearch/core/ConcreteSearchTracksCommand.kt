package de.hshl.isd.tunesearch.core

import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConcreteSearchTracksCommand(
    private val scope: CoroutineScope,
    private val tunesSearchEngine: TunesSearchEngine
) :
    SearchTracksCommand {

    suspend fun execute2(
        inDTO: SearchTracksDTO,
        successHandler: (success: List<CollectionEntity>) -> Unit,
        errorHandler: (error: Throwable) -> Unit
    ) {
        val result = tunesSearchEngine.getSongs(inDTO.term)
        result.onSuccess { successHandler(mapTracks(it)) }
        result.onFailure { errorHandler(it) }
    }


    override fun execute(
        inDTO: SearchTracksDTO,
        successHandler: (success: List<CollectionEntity>) -> Unit,
        errorHandler: (error: Throwable) -> Unit
    ) {
        scope.launch {
            //withContext(Dispatchers.IO) {
            val result = withContext(scope.coroutineContext) {
                tunesSearchEngine.getSongs(inDTO.term)
            }
            //  withContext(Dispatchers.Main) {
            result.onSuccess { successHandler(mapTracks(it)) }
            result.onFailure { errorHandler(it) }
        }
            }


    private fun mapTracks(tracks: List<TrackEntity>): List<CollectionEntity> {
        val collections = tracks.sorted().groupBy { it.collectionName }
        return collections.keys.map { CollectionEntity(it, collections.getValue(it)) }
    }
}