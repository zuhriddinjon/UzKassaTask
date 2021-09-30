package uz.instat.uzkassatask.framework.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import uz.instat.uzkassatask.busines.interactors.ApiResultHandler
import uz.instat.uzkassatask.busines.interactors.DataState
import uz.instat.fitneskittest.busines.network.abstraction.MainService
import uz.instat.fitneskittest.busines.network.data.News
import uz.instat.fitneskittest.busines.network.data.ArticleDomain
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val mainService: MainService,
    ): MainDataSource {
    override fun getLessons(): Flow<DataState<List<ArticleDomain>>> {
        return mainService.getLessons().map {
            object : ApiResultHandler<Response<News>, List<ArticleDomain>>(it) {
                override fun handleSuccess(response: Response<News>): DataState<List<ArticleDomain>> {
                    return DataState.data(response.body()?.lessonsHistory)
                }
            }.getResult()
        }
    }

    override fun getVisits(): Flow<DataState<List<VisitsHistoryDomain>>> {
        return mainService.getVisits().map {
            object : ApiResultHandler<Response<VisitsHistory>, List<VisitsHistoryDomain>>(it) {
                override fun handleSuccess(response: Response<VisitsHistory>): DataState<List<VisitsHistoryDomain>> {
                    return DataState.data(response.body()?.visitsHistory)
                }
            }.getResult()
        }

    }
}