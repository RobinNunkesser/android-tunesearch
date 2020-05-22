package de.hshl.isd.tunesearchcompose

import de.hshl.isd.basiccleanarch.Displayer
import de.hshl.isd.basiccleanarch.Presenter
import de.hshl.isd.basiccleanarch.Response
import de.hshl.isd.basiccleanarch.UseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


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
       GlobalScope.async {
           val response = gateway.search(request.term)
           when (response) {
               is Response.Success<*> -> {
                   val viewModel = presenter.present(response.value as List<TrackEntity>)
                   displayer.display(viewModel)
               }
               is Response.Failure -> {
                   displayer.display(response.error)
               }
           }
       }
    }

}