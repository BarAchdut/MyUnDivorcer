package com.example.myundivorcer.network

import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object ApiHelper {

    private val client = OkHttpClient()

    fun fetchData(url: String, callback: (String?) -> Unit) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("ApiHelper", "API call failed", e)
                Handler(Looper.getMainLooper()).post {
                    callback(null)
                }
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    Handler(Looper.getMainLooper()).post {
                        callback(responseData)
                    }
                } else {
                    Handler(Looper.getMainLooper()).post {
                        callback(null)
                    }
                }
            }
        })
    }
}
