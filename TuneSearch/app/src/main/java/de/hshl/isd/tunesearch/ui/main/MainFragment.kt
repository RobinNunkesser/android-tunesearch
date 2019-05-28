package de.hshl.isd.tunesearch.ui.main

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.fragment.findNavController
import de.hshl.isd.basiccleanarch.Displayer
import de.hshl.isd.basiccleanarch.Response
import de.hshl.isd.basiccleanarch.UseCase
import de.hshl.isd.tunesearch.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), Displayer {

    private lateinit var viewModel: TrackListViewModel
    private val interactor: UseCase<SearchRequest, TrackEntity, TrackViewModel> = Interactor(TrackPresenter())

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
            interactor.execute(SearchRequest(searchTermEditText.text.toString()), displayer = this)
            //searchButton.isEnabled = false
        }
    }

    override fun display(result: Response) {
        //searchButton.isEnabled = true
        when (result) {
            is Response.Success<*> -> {
                viewModel.submitData(result.value as List<ItemViewModel>)
                findNavController().navigate(R.id.action_mainFragment_to_trackFragment)
            }
            is Response.Failure -> {
                val builder: AlertDialog.Builder? = activity?.let {
                    AlertDialog.Builder(it)
                }
                builder?.setMessage(result.error.localizedMessage)?.setTitle(android.R.string.dialog_alert_title)
                    ?.setPositiveButton(android.R.string.ok, null)
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }
        }

    }


}
