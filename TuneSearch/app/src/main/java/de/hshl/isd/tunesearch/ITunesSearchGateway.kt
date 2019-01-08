package de.hshl.isd.tunesearch

import de.hshl.isd.tunesearch.common.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ITunesSearchGateway {

    suspend fun search(term : String): Response {
        val retrofit = Retrofit.Builder().baseUrl("https://itunes.apple.com").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(ITunesSearchAPI::class.java)
        try {
            val response = service.search(term).execute()
            return Response.Success<List<TrackEntity>>(response.body()!!.results)
        } catch (t: Throwable) {
            return Response.Failure(t)
        }

    }
}