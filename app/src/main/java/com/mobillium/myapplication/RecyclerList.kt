package com.mobillium.myapplication

import com.google.gson.annotations.SerializedName

data class RecyclerList(val results:ArrayList<RecyclerData>)
data class RecyclerData(val id:Int, val original_title:String,val overview:String,val release_date:String,val poster_path:String,val vote_average:String)
