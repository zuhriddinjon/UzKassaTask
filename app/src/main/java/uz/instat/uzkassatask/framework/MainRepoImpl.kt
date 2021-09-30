package uz.instat.uzkassatask.framework

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.instat.fitneskittest.busines.network.data.ArticleDomain
import uz.instat.fitneskittest.busines.network.data.mapper.ArticleNetworkMapper
import uz.instat.uzkassatask.busines.interactors.DataStateHandler
import uz.instat.uzkassatask.busines.interactors.UiState
import uz.instat.uzkassatask.busines.local.ArticleLocal
import uz.instat.uzkassatask.framework.datasource.MainDataSource
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val mainDataSource: MainDataSource,
    private val articleNetworkMapper: ArticleNetworkMapper,
    private val visitNetworkMapper: VisitNetworkMapper
) : MainRepo {
    override fun getLessons(): Flow<UiState<List<ArticleLocal>>> {
        return mainDataSource.getLessons().map {
            object : DataStateHandler<List<ArticleDomain>, List<ArticleLocal>>(it) {
                override fun handleSuccess(data: List<ArticleDomain>): UiState.Success<List<ArticleLocal>> {
                    return UiState.Success(articleNetworkMapper.mapFromDomainList(data))
                }

            }.getResult()
        }
    }

    override fun getVisits(): Flow<UiState<List<VisitsHistoryLocal>>> {
        return mainDataSource.getVisits().map {
            object : DataStateHandler<List<VisitsHistoryDomain>, List<VisitsHistoryLocal>>(it) {
                override fun handleSuccess(data: List<VisitsHistoryDomain>): UiState.Success<List<VisitsHistoryLocal>> {
                    return UiState.Success(visitNetworkMapper.mapFromDomainList(data))
                }

            }.getResult()
        }
    }
}