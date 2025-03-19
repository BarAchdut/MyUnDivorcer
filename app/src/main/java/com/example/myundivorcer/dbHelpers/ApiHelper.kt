package com.example.myundivorcer.network

import android.os.Handler
import android.os.Looper
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

// Data class to match the JSON structure
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

// Define the API interface
interface ApiService {
    @GET
    fun fetchPosts(@Url url: String): Call<List<Post>>
}

object ApiHelper {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/") // Using actual base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    fun fetchData(url: String, callback: (List<Post>?) -> Unit) {
        val call = apiService.fetchPosts(url)

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val responseData = response.body()
                    Handler(Looper.getMainLooper()).post {
                        callback(responseData)
                    }
                } else {
                    Log.e("ApiHelper", "API call failed with code: ${response.code()}")
                    Handler(Looper.getMainLooper()).post {
                        callback(null)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("ApiHelper", "API call failed", t)
                Handler(Looper.getMainLooper()).post {
                    callback(null)
                }
            }
        })
    }
}