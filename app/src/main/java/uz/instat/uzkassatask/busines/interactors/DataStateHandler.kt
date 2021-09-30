package uz.instat.uzkassatask.busines.interactors

import uz.instat.uzkassatask.busines.enums.Errors.Companion.error


abstract class DataStateHandler<Data, Result>(
    private val dataState: DataState<Data>
) {
    fun getResult(): UiState<Result> {
        return when (dataState.code) {
            0 -> {
                handleSuccess(dataState.data!!)
            }
            else -> {
                UiState.Error(dataState.code.error)
            }
        }
    }

    abstract fun handleSuccess(data: Data): UiState.Success<Result>
}