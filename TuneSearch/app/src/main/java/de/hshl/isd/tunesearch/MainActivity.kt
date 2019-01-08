package de.hshl.isd.tunesearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import de.hshl.isd.tunesearch.common.InputBoundary
import de.hshl.isd.tunesearch.common.OutputBoundary
import de.hshl.isd.tunesearch.common.Response
import de.hshl.isd.tunesearch.ui.main.MainFragment

class MainActivity : AppCompatActivity(), OutputBoundary {

    private val tag = "MainActivity"
    private val inputBoundary : InputBoundary<SearchRequest> = Interactor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        inputBoundary.send(SearchRequest("Jack Johnson"),outputBoundary = this)
    }

    override fun receive(response: Response) {
        when (response) {
            is Response.Success<*> -> {
                Log.i(tag, """${response.value}""")
            }
            is Response.Failure -> {
                Log.i(tag, """${response.error.localizedMessage}""")
            }
        }

    }

}
