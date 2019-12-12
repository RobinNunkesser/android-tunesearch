package de.hshl.isd.tunesearch

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import de.hshl.isd.basiccleanarch.Displayer
import de.hshl.isd.basiccleanarch.Response
import de.hshl.isd.basiccleanarch.Response.Failure
import de.hshl.isd.basiccleanarch.Response.Success
import de.hshl.isd.basiccleanarch.UseCase
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), Displayer {

    private lateinit var viewModel: TrackListViewModel
    private val interactor: UseCase<SearchRequest, TrackEntity, TrackViewModel> = SearchInteractor(
        TrackPresenter(),
        ITunesSearchGateway()
    )

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
        viewModel = ViewModelProviders.of(activity!!).get(TrackListViewModel::class.java)

        searchButton.setOnClickListener {
            interactor.execute(SearchRequest(searchTermEditText.text.toString()), displayer = this)
            //searchButton.isEnabled = false
        }
    }

    override fun display(result: Response) {
        //searchButton.isEnabled = true
        when (result) {
            is Success<*> -> {
                viewModel.data = result.value as List<ItemViewModel>
                findNavController().navigate(R.id.action_mainFragment_to_trackFragment)
            }
            is Failure -> {
                activity?.let {
                    val builder = AlertDialog.Builder(it)
                    val dialog = builder.setMessage(result.error.localizedMessage)
                        .setTitle(android.R.string.dialog_alert_title)
                        .setPositiveButton(android.R.string.ok, null).create()
                    dialog.show()
                }
            }
        }

    }


}