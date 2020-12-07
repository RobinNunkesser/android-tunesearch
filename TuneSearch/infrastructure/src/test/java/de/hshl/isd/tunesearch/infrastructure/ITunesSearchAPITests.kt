package de.hshl.isd.tunesearch.infrastructure

import junit.framework.TestCase.fail
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ITunesSearchAPITests {
    @Test
    fun testSearch() {
        val retrofit =
            Retrofit.Builder().baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ITunesSearchAPI::class.java)
        try {
            val response = service.search("Jack+Johnson").execute()
            assert(response.body()!!.results.size == 50)
        } catch (t: Throwable) {
            fail(t.localizedMessage)
        }
    }
}

