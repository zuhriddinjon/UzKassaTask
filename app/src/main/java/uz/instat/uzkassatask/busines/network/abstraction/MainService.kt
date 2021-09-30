package uz.instat.fitneskittest.busines.network.abstraction

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import uz.instat.uzkassatask.busines.interactors.ApiResult
import uz.instat.fitneskittest.busines.network.data.News

interface MainService {
    fun getLessons(): Flow<ApiResult<Response<News>?>>
    fun getVisits(): Flow<ApiResult<Response<VisitsHistory>?>>
}