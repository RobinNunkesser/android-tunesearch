package de.hshl.isd.tunesearch.ui.main

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import androidx.navigation.fragment.findNavController
import de.hshl.isd.tunesearch.*
import de.hshl.isd.tunesearch.common.InputBoundary
import de.hshl.isd.tunesearch.common.OutputBoundary
import de.hshl.isd.tunesearch.common.Response
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), OutputBoundary {

    private lateinit var viewModel: TrackListViewModel
    private val inputBoundary: InputBoundary<SearchRequest> = Interactor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(TrackListViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        searchButton.setOnClickListener {
            inputBoundary.send(SearchRequest(searchTermEditText.text.toString()), outputBoundary = this)
            //searchButton.isEnabled = false
        }
    }

    override fun receive(response: Response) {
        //searchButton.isEnabled = true
        when (response) {
            is Response.Success<*> -> {
                viewModel.submitData(response.value as List<ItemViewModel>)
                findNavController().navigate(R.id.action_mainFragment_to_trackFragment)
            }
            is Response.Failure -> {
                val builder: AlertDialog.Builder? = activity?.let {
                    AlertDialog.Builder(it)
                }
                builder?.setMessage(response.error.localizedMessage)?.setTitle(android.R.string.dialog_alert_title)?.setPositiveButton(android.R.string.ok, null)
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }
        }

    }


}
