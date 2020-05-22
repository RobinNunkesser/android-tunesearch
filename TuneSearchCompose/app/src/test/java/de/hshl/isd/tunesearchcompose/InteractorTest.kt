package de.hshl.isd.tunesearch

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import de.hshl.isd.basiccleanarch.Displayer
import de.hshl.isd.basiccleanarch.Response
import de.hshl.isd.tunesearchcompose.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.CompletableFuture

class InteractorTest {

/*    private class MockDisplayer(val future: CompletableFuture<Int>) :
        Displayer {
        override fun display(result: Response) {
            future.complete(1)
        }

    }

    @Test
    fun testCallDisplayer() {
        val future = CompletableFuture<Int>()

        val model = TrackEntity("B", "XY", "B", 1, 1, "", "")

        val mockGateway = mock<ITunesSearchGateway> {
            onBlocking { search("term") } doReturn Response.Success<List<TrackEntity>>(listOf(model))
        }

        val mockPresenter = mock<TrackPresenter> {
            on { present(model) } doReturn TrackViewModel("", "", "")
        }

        SearchInteractor(mockPresenter, mockGateway).execute(
            SearchRequest("term"),
            MockDisplayer(future)
        )
        assertEquals(1, future.get() as Int)
    }*/
}