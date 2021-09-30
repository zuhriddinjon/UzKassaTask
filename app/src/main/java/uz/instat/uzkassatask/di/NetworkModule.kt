package uz.instat.fitneskittest.di

import android.content.Context
import android.net.ConnectivityManager
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.instat.uzkassatask.busines.network.NetworkConstants
import uz.instat.fitneskittest.busines.network.mainInterface.MainInterface
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val baseUrl = "https://newsapi.org/"

    @Singleton
    @Provides
    fun provideGson()
            : Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(NetworkConstants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(NetworkConstants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClient.addInterceptor {
            val request = it.request()
                .newBuilder()
                .build()
            it.proceed(request)
        }
        okHttpClient.addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Singleton
    @Provides
    fun provideConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    @Singleton
    @Provides
    fun provideMainInterface(
        retrofit: Retrofit
    ): MainInterface {
        return retrofit.create(MainInterface::class.java)
    }

}