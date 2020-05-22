package de.hshl.isd.tunesearchcompose

import de.hshl.isd.basiccleanarch.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ITunesSearchGateway {

    open suspend fun search(term: String): Response {
        val retrofit =
            Retrofit.Builder().baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ITunesSearchAPI::class.java)
        try {
            val response = service.search(term).execute()
            return Response.Success(response.body()!!.results)
        } catch (t: Throwable) {
            return Response.Failure(t)
        }

    }
}