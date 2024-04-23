package com.ekremkocak.mybase.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ekremkocak.mybase.data.model.SearchPhoto
import com.ekremkocak.mybase.data.network.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel(){
    private var queryString: String? = savedStateHandle["plantName"]


    private val _plantPictures = MutableStateFlow<PagingData<SearchPhoto>?>(null)
    val plantPictures: Flow<PagingData<SearchPhoto>> get() {
        return _plantPictures.filterNotNull()
    }

    init {
        refreshData()
    }


    fun refreshData() {

        viewModelScope.launch {
            try {
                _plantPictures.value = searchRepository.getSearchResultStream(queryString ?: "").cachedIn(viewModelScope).first()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}