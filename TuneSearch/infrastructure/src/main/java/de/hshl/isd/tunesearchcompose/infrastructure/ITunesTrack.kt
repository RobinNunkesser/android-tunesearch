package de.hshl.isd.tunesearchcompose.infrastructure

data class ITunesTrack(
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val trackNumber: Int,
    val discNumber: Int,
    val artworkUrl60: String,
    val artworkUrl100: String
) : Comparable<ITunesTrack> {
    override fun compareTo(other: ITunesTrack): Int {
        if (!collectionName.equals(other.collectionName)) return collectionName.compareTo(other.collectionName)
        if (discNumber != other.discNumber) return discNumber.compareTo(other.discNumber)
        return trackNumber.compareTo(other.trackNumber)
    }
}