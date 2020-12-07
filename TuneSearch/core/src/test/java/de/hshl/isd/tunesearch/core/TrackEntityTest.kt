package de.hshl.isd.tunesearch.core

import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.TrackEntity
import org.junit.Assert.assertTrue
import org.junit.Test

class TrackEntityTest {
    @Test
    fun sort_sameCollection() {
        val track1 = TrackEntity("B", "XY", "B", 1, 1, "")
        val track2 = TrackEntity("A", "XY", "A", 2, 1, "")
        val track3 = TrackEntity("A", "XY", "A", 1, 2, "")
        assertTrue(track1 < track2)
        assertTrue(track2 < track3)
    }
}
