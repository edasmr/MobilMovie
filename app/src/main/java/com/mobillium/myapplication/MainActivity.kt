package com.mobillium.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.mobillium.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var nowPlayingListener:IListeler
    private lateinit var upComingListener:IListeler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        val viewModel = makeApiCall()
        makeApiCallnowPlay()

        setupBinding(viewModel)



        nowPlayingListener = object : IListeler {
            override fun onClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("id", viewModel.pagerListData.value?.results?.get(position)?.id)
                startActivity(intent)
            }
        }

        upComingListener = object : IListeler {
            override fun onClick(position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("id", viewModel.recyclerListData.value?.results?.get(position)?.id)
                startActivity(intent)
            }
        }


    }

    fun setupBinding(viewModel: MainActivityViewModel) {

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.setVariable(BR.viewModel,  viewModel)
        activityMainBinding.executePendingBindings()

        activityMainBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }

        activityMainBinding.nowPlayRecycler.apply {

            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL ,false)
           val decoration  = DividerItemDecoration(this@MainActivity, HORIZONTAL)
           addItemDecoration(decoration)

        }


    }

    fun setupBindingNowPlaying(viewModel: MainActivityViewModel) {

        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.setVariable(BR.viewModel,  viewModel)
        activityMainBinding.executePendingBindings()


        activityMainBinding.nowPlayRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL ,false)

            val decoration  = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }

    }
    fun makeApiCall(): MainActivityViewModel {
        val viewModel =  ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, androidx.lifecycle.Observer {
            Log.i("asd", "asd")

            if(it != null) {
                //update the adapter
                viewModel.setAdapterData(it.results, upComingListener)
            } else {
                Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })


        viewModel.getUpComing()


        return viewModel
    }

    fun makeApiCallnowPlay():MainActivityViewModel{
        val viewModel =  ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getpagerListDataObserver().observe(this, androidx.lifecycle.Observer {
            Log.i("dsd", "asd")


            if(it != null) {
                //update the adapter
                viewModel.setAdapterDataNowPlay(it.results, nowPlayingListener)
            } else {
                Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })


        viewModel.getNowPlaying()

        return viewModel

    }
}