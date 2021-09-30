package uz.instat.uzkassatask

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp: Application() {
    override fun onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate()
        appContext = this
        appResolver = contentResolver
        appResources = resources
    }

    companion object {
        lateinit var appContext: Context
        lateinit var appResources: Resources
        lateinit var appResolver: ContentResolver
    }
}