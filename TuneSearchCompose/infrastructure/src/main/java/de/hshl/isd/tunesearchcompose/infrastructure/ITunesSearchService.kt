package de.hshl.isd.tunesearchcompose.infrastructure

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ITunesSearchService : ITunesSearchAPI {

    val retrofit =
        Retrofit.Builder().baseUrl("https://itunes.apple.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val service = retrofit.create(ITunesSearchAPI::class.java)

    override fun search(term: String): Call<ResponseEntity> =
        service.search(term)
}