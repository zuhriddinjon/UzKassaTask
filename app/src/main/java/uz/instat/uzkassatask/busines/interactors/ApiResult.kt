package uz.instat.uzkassatask.busines.interactors

sealed class ApiResult<out T> {
    class Success<out T>(val value: T) : ApiResult<T>()
    class GenericError(
        var code: Int? = null,
    ) : ApiResult<Nothing>()

    object NetworkError : ApiResult<Nothing>()
}