package com.example.midtermapplication

import android.util.Log.d
import com.google.gson.annotations.Until
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object GameDataLoader {

    private var gameUrl = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://api.rawg.io/api/")
        .build()

    private var service = gameUrl.create(GameController::class.java)

    fun getGames(size: String, customCallBack: CustomCallBack) {
        val call = service.getGames(size)
        call.enqueue(callback(customCallBack))
    }


    private fun callback(customCallBack: CustomCallBack) =
        object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                d("getRequest", "${t.message}")
                customCallBack.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                d("getRequest", "${response.body()}")
                customCallBack.onSuccess(response.body().toString())
            }
        }

}


interface GameController {
    @GET("games?filters[0][operator]=equals")
    fun getGames(@Query("filters[0][size]") size: String): Call<String>
}

//https://api.rawg.io/api/