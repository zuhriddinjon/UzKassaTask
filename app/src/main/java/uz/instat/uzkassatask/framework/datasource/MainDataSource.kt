package uz.instat.uzkassatask.framework.datasource

import kotlinx.coroutines.flow.Flow
import uz.instat.uzkassatask.busines.interactors.DataState
import uz.instat.fitneskittest.busines.network.data.ArticleDomain

interface MainDataSource {
    fun getLessons(): Flow<DataState<List<ArticleDomain>>>
    fun getVisits(): Flow<DataState<List<VisitsHistoryDomain>>>
}