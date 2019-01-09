package de.hshl.isd.tunesearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.findNavController
import de.hshl.isd.tunesearch.common.InputBoundary
import de.hshl.isd.tunesearch.common.OutputBoundary
import de.hshl.isd.tunesearch.common.Response
import de.hshl.isd.tunesearch.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_info -> LicenseDialogFragment().show(supportFragmentManager, "Dialog")
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }


}
