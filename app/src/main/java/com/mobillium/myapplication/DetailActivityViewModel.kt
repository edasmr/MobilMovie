package com.mobillium.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel: ViewModel()
{
    var detailData :MutableLiveData<RecyclerData> = MutableLiveData()
    init {
        detailData = MutableLiveData()
    }

    fun getDetailListDataObserver():MutableLiveData<RecyclerData>{
        return detailData
    }

    fun getDetailMovie(id:Int){
        val retroInstance =RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getMovie(id.toString(), RetroInstance.API_KEY)
        call.enqueue(object : Callback<RecyclerData> {
            override fun onResponse(call: Call<RecyclerData>, response: Response<RecyclerData>) {

                if (response.isSuccessful) {
                    detailData.postValue(response.body())
                } else {
                    detailData.postValue(null)
                }

            }

            override fun onFailure(call: Call<RecyclerData>, t: Throwable) {
                detailData.postValue(null)
            }

        })

    }
}