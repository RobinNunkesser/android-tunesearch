package de.hshl.isd.tunesearchcompose.infrastructure

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesSearchAPI {
    @GET("search?entity=song&country=de")
    fun search(@Query("term") term : String): Call<ResponseEntity>

}