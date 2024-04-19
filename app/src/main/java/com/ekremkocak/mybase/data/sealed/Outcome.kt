package com.prisonersoftware.tasarrufrobotu.data.sealed

sealed class Outcome<out T> {
    data object EMPTY : Outcome<Nothing>()
    data object PROGRESS : Outcome<Nothing>()
    data class SUCCESS<out T>(val data: T?) : Outcome<T>()
    data class FAILURE(val errorMessage: String, val code: Int? = -1) : Outcome<Nothing>()
}
