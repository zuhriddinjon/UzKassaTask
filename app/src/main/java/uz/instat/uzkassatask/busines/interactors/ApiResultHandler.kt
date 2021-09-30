package uz.instat.uzkassatask.busines.interactors

import uz.instat.uzkassatask.busines.enums.Errors
import uz.instat.uzkassatask.busines.utils.isNetworkAvailable


abstract class ApiResultHandler<Data, Result>(
    private val response: ApiResult<Data?>,
) {
    fun getResult(): DataState<Result> {

        return when (response) {

            is ApiResult.GenericError -> {
                if (response.code == 0 || response.code == null) response.code =
                    Errors.UNKNOWN_ERROR.code
                DataState.error(response.code ?: Errors.UNKNOWN_ERROR.code)
            }

            is ApiResult.NetworkError -> {
                DataState.error(if (isNetworkAvailable) Errors.SERVER_ERROR.code else Errors.NO_CONNECTION.code)
            }

            is ApiResult.Success -> {
                if (response.value == null) {
                    DataState.error(Errors.EMPTY_DATA.code)
                } else {
                    handleSuccess(response.value)
                }
            }

        }
    }

    abstract fun handleSuccess(response: Data): DataState<Result>
}