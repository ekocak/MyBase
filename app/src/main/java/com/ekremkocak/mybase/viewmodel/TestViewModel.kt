package com.ekremkocak.mybase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ekremkocak.mybase.data.model.Category
import com.ekremkocak.mybase.data.room.CategoriesDao
import com.ekremkocak.mybase.data.room.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
): ViewModel() {


    val plantAndGardenPlantings =
        categoriesRepository.getCategories(10)

    suspend fun addCategory(category: Category){
        categoriesRepository.addCategory(category)
    }


}