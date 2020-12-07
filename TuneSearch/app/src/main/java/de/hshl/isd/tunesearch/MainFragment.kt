package de.hshl.isd.tunesearch

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.CollectionEntity
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.SearchTracksDTO
import de.hshl.isd.tunesearch.core.ConcreteSearchTracksCommand
import de.hshl.isd.tunesearch.infrastructure.adapters.TunesSearchEngineAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var viewModel: TrackListViewModel

    val service = ConcreteSearchTracksCommand(TunesSearchEngineAdapter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TrackListViewModel::class.java)

        searchButton.setOnClickListener {
            service.execute(
                SearchTracksDTO(searchTermEditText.text.toString()),
                ::success,
                ::failure
            )
            //searchButton.isEnabled = false
        }
    }

    fun success(collections: List<CollectionEntity>) {
        val trackList: MutableList<ItemViewModel> = mutableListOf()
        for (collection in collections) {
            trackList.add(ItemViewModel(collection.name))
            for (track in collection.tracks) {
                trackList.add(
                    TrackViewModel(
                        track.artistName,
                        track.artworkUrl,
                        "${track.trackNumber} - ${track.trackName}"
                    )
                )
            }
        }
        viewModel.data = trackList
        findNavController().navigate(R.id.action_mainFragment_to_trackFragment)
    }

    fun failure(error: Throwable) {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            val dialog = builder.setMessage(error.localizedMessage)
                .setTitle(android.R.string.dialog_alert_title)
                .setPositiveButton(android.R.string.ok, null).create()
            dialog.show()
        }
    }


}
