package de.hshl.isd.tunesearch

import de.hshl.isd.tunesearchcompose.TrackEntity
import org.junit.Assert.assertTrue
import org.junit.Test

class TrackEntityTest {
    @Test
    fun sort_sameCollection() {
        val track1 = TrackEntity("B", "XY", "B", 1, 1, "", "")
        val track2 = TrackEntity("A", "XY", "A", 2, 1, "", "")
        val track3 = TrackEntity("A", "XY", "A", 1, 2, "", "")
        assertTrue(track1.compareTo(track2) < 0)
        assertTrue(track2.compareTo(track3) < 0)
    }
}
