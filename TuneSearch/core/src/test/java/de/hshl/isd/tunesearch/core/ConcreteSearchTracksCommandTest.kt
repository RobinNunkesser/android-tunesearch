package de.hshl.isd.tunesearch.core

import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.SearchTracksDTO
import de.hshl.isd.tunesearch.infrastructure.adapters.TunesSearchEngineAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class ConcreteSearchTracksCommandTest {

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun execute() {
        runBlocking {
            ConcreteSearchTracksCommand(MainScope(), TunesSearchEngineAdapter())
                .execute2(SearchTracksDTO("Jack+Johnson"), {
                    assertTrue(it.size == 50)
                }, {
                    fail(it.toString())
                })
        }

    }
}
