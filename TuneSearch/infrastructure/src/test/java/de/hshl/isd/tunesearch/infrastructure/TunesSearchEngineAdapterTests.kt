package de.hshl.isd.tunesearch.infrastructure

import de.hshl.isd.tunesearch.infrastructure.adapters.TunesSearchEngineAdapter
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TunesSearchEngineAdapterTests {
    @Test
    fun testSearch() {
        runBlocking {
            val result = TunesSearchEngineAdapter().getSongs("Jack+Johnson")
            result.onSuccess { assert(it.size == 50) }
            result.onFailure { fail(it.localizedMessage) }
        }
    }
}