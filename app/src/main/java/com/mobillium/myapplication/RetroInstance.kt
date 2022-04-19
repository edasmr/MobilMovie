package com.mobillium.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        val API_KEY = "d0dc140208f0877d874bcc49732427e3";
        val BASE_URL ="https://api.themoviedb.org/3/movie/"

        fun  getRetroInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}