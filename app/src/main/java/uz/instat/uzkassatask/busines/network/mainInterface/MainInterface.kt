package uz.instat.fitneskittest.busines.network.mainInterface

import retrofit2.Response
import retrofit2.http.GET
import uz.instat.uzkassatask.busines.network.NetworkConstants
import uz.instat.fitneskittest.busines.network.data.News

interface MainInterface {

    @GET(NetworkConstants.LESSON_HISTORY)
    suspend fun lessonHistory(): Response<News>

    @GET(NetworkConstants.VISITS_HISTORY)
    suspend fun visitsHistory(): Response<VisitsHistory>

}