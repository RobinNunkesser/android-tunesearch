package de.hshl.isd.tunesearch

import de.hshl.isd.tunesearch.common.InputBoundary
import de.hshl.isd.tunesearch.common.OutputBoundary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class Interactor : InputBoundary<SearchRequest> {
    override fun send(request: SearchRequest, outputBoundary: OutputBoundary) {
       GlobalScope.async { outputBoundary.receive(ITunesSearchGateway().search(request.term))}
    }
}