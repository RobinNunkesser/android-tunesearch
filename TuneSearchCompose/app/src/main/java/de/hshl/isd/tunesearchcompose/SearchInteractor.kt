package de.hshl.isd.tunesearchcompose

import de.hshl.isd.basiccleanarch.Displayer
import de.hshl.isd.basiccleanarch.Presenter
import de.hshl.isd.basiccleanarch.Response
import de.hshl.isd.basiccleanarch.UseCase
import kotlinx.coroutines.*


class SearchInteractor(
    override val presenter: Presenter<List<TrackEntity>, Map<String, List<TrackViewModel>>>,
    val gateway: ITunesSearchGateway
) :
    UseCase<SearchRequest, List<TrackEntity>, Map<String, List<TrackViewModel>>> {

    override fun execute(
        request: SearchRequest,
        displayer: Displayer<Map<String, List<TrackViewModel>>>,
        requestCode: Int
    ) {
        val scope = MainScope()
       scope.launch {
           withContext(Dispatchers.IO) {
           when (val response = gateway.search(request.term)) {
               is Response.Success<*> -> {
                   val viewModel = presenter.present(response.value as List<TrackEntity>)
                   withContext(Dispatchers.Main) {
                       displayer.display(viewModel)
                   }
               }
               is Response.Failure -> {
                   withContext(Dispatchers.Main) {
                       displayer.display(response.error)
                   }
               }
           }
           }
       }
    }

}