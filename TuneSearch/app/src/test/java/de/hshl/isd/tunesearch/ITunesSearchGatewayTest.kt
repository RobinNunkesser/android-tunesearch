package de.hshl.isd.tunesearch

import de.hshl.isd.basiccleanarch.Response
import junit.framework.Assert.assertTrue
import junit.framework.Assert.fail
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ITunesSearchGatewayTest {
    @Test
    fun testFetchData() {
        runBlocking {
            val result = ITunesSearchGateway().search("Jack+Johnson")
            when (result) {
                is Response.Success<*> -> {
                    val model = result.value as List<TrackEntity>
                    assertTrue(model.size > 0)
                }
                is Response.Failure -> {
                    fail(result.error.localizedMessage)
                }
            }
        }
    }
}
