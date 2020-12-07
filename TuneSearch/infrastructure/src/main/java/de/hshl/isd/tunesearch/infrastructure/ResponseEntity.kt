package de.hshl.isd.tunesearch.infrastructure

data class ResponseEntity(val resultCount : Int, val results: List<TrackEntity>)

