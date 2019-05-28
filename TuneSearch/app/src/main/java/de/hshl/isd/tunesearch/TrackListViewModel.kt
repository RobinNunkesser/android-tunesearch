package de.hshl.isd.tunesearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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