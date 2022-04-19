package com.mobillium.myapplication

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    @GET("upcoming")
    fun getUpComing(@Query("api_key")api_key:String): Call<RecyclerList>

    @GET("now_playing")
    fun getNowPlaying(@Query("api_key")api_key:String): Call<RecyclerList>

    @GET
    fun getMovie(@Url movie_id:String, @Query("api_key")api_key:String): Call<RecyclerData>

}