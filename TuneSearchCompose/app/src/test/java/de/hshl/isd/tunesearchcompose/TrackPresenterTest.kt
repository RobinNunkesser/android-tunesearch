package de.hshl.isd.tunesearch

import de.hshl.isd.tunesearchcompose.TrackEntity
import de.hshl.isd.tunesearchcompose.TrackPresenter
import org.junit.Assert
import org.junit.Test

class TrackPresenterTest {
    private val artist = "artist"
    private val url100 = "url100"
    private val trackNumber = 42
    private val trackName = "trackName"

    @Test
    fun testPresent() {
        val model = TrackEntity(artist, "XY", trackName, trackNumber, 1, "", url100)
        val viewModel = TrackPresenter().present(model)
        Assert.assertEquals(artist, viewModel.subtitle)
        Assert.assertEquals(url100, viewModel.image)
        Assert.assertEquals("${trackNumber} - ${trackName}", viewModel.title)
    }

}