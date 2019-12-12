package de.hshl.isd.tunesearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_track_list.*


/**
 * A fragment representing a list of Items.
 */
class TrackFragment : Fragment() {

    private lateinit var viewModel: TrackListViewModel
    private val adapter = TrackListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_track_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(TrackListViewModel::class.java)

        adapter.submitList(viewModel.data)

        list.adapter = adapter

    }


}
