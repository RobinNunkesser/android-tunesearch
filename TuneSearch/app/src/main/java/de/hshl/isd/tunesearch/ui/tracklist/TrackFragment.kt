package de.hshl.isd.tunesearch.ui.tracklist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.hshl.isd.tunesearch.R
import de.hshl.isd.tunesearch.TrackListAdapter
import de.hshl.isd.tunesearch.TrackListViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(TrackListViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        viewModel.data.observe(this, Observer { adapter.submitList(it) })

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter

    }


}
