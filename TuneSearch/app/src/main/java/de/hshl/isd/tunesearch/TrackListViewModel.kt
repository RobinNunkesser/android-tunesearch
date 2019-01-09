package de.hshl.isd.tunesearch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class TrackListViewModel : ViewModel() {

    private val _data = MutableLiveData<List<ItemViewModel>>()

    val data: LiveData<List<ItemViewModel>>
        get() = _data

    init {
        _data.value = listOf()
    }

    fun submitData(items : List<ItemViewModel>) {
        _data.postValue(items)
    }
}