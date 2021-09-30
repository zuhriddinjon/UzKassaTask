package uz.instat.uzkassatask.busines.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import uz.instat.uzkassatask.NewsApp

fun getString(@StringRes id: Int) = NewsApp.appResources.getString(id)
fun getStringArray(@ArrayRes id: Int): Array<String> = NewsApp.appResources.getStringArray(id)

fun getIntArray(@ArrayRes id: Int): IntArray = NewsApp.appResources.getIntArray(id)

@ColorInt
fun getManualColor(@ColorRes id: Int): Int = NewsApp.appContext.let {
    return ContextCompat.getColor(NewsApp.appContext, id)
}

fun Context.toast(text: String) {
    val toast = Toast.makeText(this, text, Toast.LENGTH_LONG)
    toast.setText(text)
    toast.duration = Toast.LENGTH_LONG
    toast.show()
}

val isNetworkAvailable: Boolean
    get() {
        var isConnected = false
        val manager =
            NewsApp.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.allNetworks.forEach { network ->
            manager.getNetworkCapabilities(network)?.let { capabilities ->
                isConnected =
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                if (isConnected) return@forEach
            }
        }
        return isConnected
    }

fun getDimen(@DimenRes id: Int) = NewsApp.appContext.resources.getDimension(id).toInt()

@SuppressLint("MissingPermission")
fun vibrate(pattern: LongArray) {
    if (Build.VERSION.SDK_INT >= 26) {
        (NewsApp.appContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
            VibrationEffect.createWaveform(pattern, -1)
        )
    } else {
        (NewsApp.appContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(
            pattern,
            -1
        )
    }
}
