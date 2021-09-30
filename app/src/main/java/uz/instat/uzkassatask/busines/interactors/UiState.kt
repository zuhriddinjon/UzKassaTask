package uz.instat.uzkassatask.busines.interactors

import uz.instat.uzkassatask.busines.enums.Errors


sealed class UiState<T>(val data: T?, val error: Errors? = null) {
    class Success<T>(data: T?) : UiState<T>(data)
    class Error<T>(error: Errors?) : UiState<T>(null, error)
    class Loading<T> : UiState<T>(null)
}