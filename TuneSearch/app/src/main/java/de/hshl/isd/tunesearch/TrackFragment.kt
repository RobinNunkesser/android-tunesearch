package de.hshl.isd.tunesearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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
