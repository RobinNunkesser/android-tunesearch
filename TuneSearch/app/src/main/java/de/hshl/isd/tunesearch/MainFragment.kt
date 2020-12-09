package de.hshl.isd.tunesearch

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.CollectionEntity
import de.hshl.isd.tunesearch.core.ConcreteSearchTracksCommand
import de.hshl.isd.tunesearch.infrastructure.adapters.TunesSearchEngineAdapter
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private lateinit var viewModel: TrackListViewModel

    val service = ConcreteSearchTracksCommand(MainScope(), TunesSearchEngineAdapter())

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
            // TODO: This should be in the core... why is it not working there??
            MainScope().launch {
                withContext(Dispatchers.IO) {
                    val result =
                        TunesSearchEngineAdapter().getSongs(searchTermEditText.text.toString())
                    result.onSuccess { tracks ->
                        val collections = tracks.sorted().groupBy { it.collectionName }
                        val mappedCollections = collections.keys.map {
                            CollectionEntity(it, collections.getValue(it))
                        }
                        this@MainFragment.success(mappedCollections)
                    }
                }

            }
            /*MainScope().launch {
            service.execute2(
                SearchTracksDTO(searchTermEditText.text.toString()),
                ::success,
                ::failure
            )
        }*/
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



