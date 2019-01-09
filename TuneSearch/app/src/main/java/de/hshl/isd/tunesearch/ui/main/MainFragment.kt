package de.hshl.isd.tunesearch.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import de.hshl.isd.tunesearch.*
import de.hshl.isd.tunesearch.common.InputBoundary
import de.hshl.isd.tunesearch.common.OutputBoundary
import de.hshl.isd.tunesearch.common.Response
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), OutputBoundary {

    private lateinit var viewModel: TrackListViewModel
    private val inputBoundary: InputBoundary<SearchRequest> = Interactor()

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
        }
    }

    override fun receive(response: Response) {
        when (response) {
            is Response.Success<*> -> {
                val tracks = (response.value as List<TrackEntity>).sorted()
                val collections = tracks.groupBy { it.collectionName }
                val trackList: MutableList<ItemViewModel> = mutableListOf()
                for (collection in collections.keys) {
                    trackList.add(ItemViewModel(collection))
                    for (track in collections[collection]!!) {
                        trackList.add(TrackViewModel(track.artistName, track.artworkUrl100, "${track.trackNumber} - ${track.trackName}"))
                    }
                }
                viewModel.submitData(trackList)

                findNavController().navigate(R.id.action_mainFragment_to_trackFragment)

            }
            is Response.Failure -> {
                Log.i(tag, """${response.error.localizedMessage}""")
            }
        }

    }


}
