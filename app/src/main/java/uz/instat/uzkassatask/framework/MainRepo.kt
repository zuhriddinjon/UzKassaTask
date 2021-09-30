package uz.instat.uzkassatask.framework

import kotlinx.coroutines.flow.Flow
import uz.instat.uzkassatask.busines.interactors.UiState
import uz.instat.uzkassatask.busines.local.ArticleLocal

interface MainRepo {
    fun getLessons(): Flow<UiState<List<ArticleLocal>>>
    fun getVisits(): Flow<UiState<List<VisitsHistoryLocal>>>
}