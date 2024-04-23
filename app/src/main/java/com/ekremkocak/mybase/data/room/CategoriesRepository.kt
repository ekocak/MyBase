package com.ekremkocak.mybase.data.room

import com.ekremkocak.mybase.data.model.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(
    private val categoriesDao: CategoriesDao,
    ) {

    suspend fun addCategory(category: Category): Long {
        return when (val local = categoriesDao.getCategoryWithName(category.name)) {
            null -> categoriesDao.insert(category)
            else -> local.id
        }
    }

    fun getCategories(limit: Int) = categoriesDao.categoriesSortedByPodcastCount(limit)



}
