package de.hshl.isd.tunesearch

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.webkit.WebView

class LicenseDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_licenses, null) as WebView
        view.loadUrl("file:///android_asset/open_source_licenses.html")
        return AlertDialog.Builder(activity,
                R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle(getString(R.string.action_licenses))
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create()
    }

}