package de.hshl.isd.tunesearchcompose.core.ports

data class TrackEntity(
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val trackNumber: Int,
    val discNumber: Int,
    val artworkUrl: String
) : Comparable<TrackEntity> {
    override fun compareTo(other: TrackEntity): Int {
        if (collectionName != other.collectionName) return collectionName.compareTo(other.collectionName)
        if (discNumber != other.discNumber) return discNumber.compareTo(other.discNumber)
        return trackNumber.compareTo(other.trackNumber)
    }
}