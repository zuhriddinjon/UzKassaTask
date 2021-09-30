package uz.instat.uzkassatask.busines.utils

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import uz.instat.uzkassatask.busines.enums.Errors
import uz.instat.uzkassatask.busines.interactors.ApiResult
import uz.instat.uzkassatask.busines.network.NetworkConstants.NETWORK_TIMEOUT
import java.io.IOException


suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?,
): ApiResult<T?> {
    return withContext(dispatcher) {
        try {
            withTimeout(NETWORK_TIMEOUT) {
                ApiResult.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    ApiResult.GenericError(
                        Errors.NETWORK_TIMEOUT_ERROR.code
                    )
                }
                is IOException -> {
                    ApiResult.NetworkError
                }
                is HttpException -> {
                    when (throwable.code()) {
                        401 -> {
                            ApiResult.GenericError(
                                401
                            )
                        }
                        404 -> {
                            ApiResult.GenericError(
                                404
                            )
                        }
                        else -> {
                            val errorResponse = convertErrorBody(throwable)
                            ApiResult.GenericError(
                                errorResponse.code()
                            )
                        }
                    }
                }
                else -> {
                    ApiResult.GenericError(
                        Errors.UNKNOWN_ERROR.code
                    )
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): Response<*> {
    return try {
        Gson().fromJson(throwable.response()?.errorBody()?.charStream(), Response::class.java)
    } catch (exception: Exception) {
        Response.error<Any>(
            Errors.UNKNOWN_ERROR.code,
            ResponseBody.create(MediaType.get("Unknown error"), "Unknown error")
        )
    }
}




