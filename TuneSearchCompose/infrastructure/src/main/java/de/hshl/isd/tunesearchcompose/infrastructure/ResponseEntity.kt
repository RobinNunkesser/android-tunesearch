package de.hshl.isd.tunesearchcompose.infrastructure

data class ResponseEntity(val resultCount : Int, val results: List<ITunesTrack>)
