package com.mobillium.myapplication

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel:ViewModel() {
    var recyclerListData :MutableLiveData<RecyclerList> = MutableLiveData()
    var pagerListData :MutableLiveData<RecyclerList> = MutableLiveData()

    lateinit var recyclerViewAdapter : RecyclerViewAdapter
    lateinit var pagerAdapter: PagerAdapter


    init {
        recyclerListData = MutableLiveData()
        pagerListData = MutableLiveData()
        recyclerViewAdapter = RecyclerViewAdapter()
        pagerAdapter = PagerAdapter()
    }

    fun getAdapter():  RecyclerViewAdapter{

        return recyclerViewAdapter

    }
    fun getPageAdapter():  PagerAdapter{
        return pagerAdapter

    }

    fun setAdapterData(data: ArrayList<RecyclerData>, listener:IListeler) {
        recyclerViewAdapter.setDataList(data, listener)
        recyclerViewAdapter.notifyDataSetChanged()

    }

    fun setAdapterDataNowPlay(data: ArrayList<RecyclerData>, listener: IListeler) {
        pagerAdapter.setDataList(data, listener)
        pagerAdapter.notifyDataSetChanged()
    }





    fun getRecyclerListDataObserver():MutableLiveData<RecyclerList>{
        return recyclerListData
    }

    fun getpagerListDataObserver():MutableLiveData<RecyclerList>{
        return pagerListData
    }

    fun getUpComing(){
        val retroInstance =RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUpComing(RetroInstance.API_KEY)
        call.enqueue(object : Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {

                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

        })

    }

    fun getNowPlaying(){
        val retroInstance =RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getNowPlaying(RetroInstance.API_KEY)
        call.enqueue(object : Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {

                if (response.isSuccessful) {
                    pagerListData.postValue(response.body())
                } else {
                    pagerListData.postValue(null)
                }

            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                pagerListData.postValue(null)
            }

        })

    }


}