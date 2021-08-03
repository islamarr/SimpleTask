package com.islam.task.generalUtils

import android.content.Context
import android.net.ConnectivityManager
import org.kodein.di.android.x.BuildConfig

object Utils {

    const val DEVURL = "https://raw.githubusercontent.com/"
    const val URL = "https://raw.githubusercontent.com/"

    fun getUrl(): String {
        return if (BuildConfig.DEBUG)
            DEVURL
        else
            URL
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}