 package com.mobillium.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mobillium.myapplication.databinding.ActivityDetailBinding

 class DetailActivity : AppCompatActivity() {
     private lateinit var activityMainBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id:Int
        id = intent.extras!!?.getInt("id")

        val viewModel = makeApiCall(id)
        setupBinding(viewModel)
    }

     private fun setupBinding(viewModel: DetailActivityViewModel) {
         activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
         activityMainBinding.setVariable(BR.viewModel,  viewModel)
         activityMainBinding.executePendingBindings()

     }

     fun makeApiCall(id:Int): DetailActivityViewModel {
        val viewModel =  ViewModelProviders.of(this).get(DetailActivityViewModel::class.java)
        viewModel.getDetailListDataObserver().observe(this, androidx.lifecycle.Observer {
            Log.i("asd", "asd")

            if(it != null) {
                //update the adapter
                //viewModel.setAdapterData(it.results)
                activityMainBinding.recyclerData = it

            } else {
                Toast.makeText(this@DetailActivity, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.getDetailMovie(id)


        return viewModel
    }

     companion object{
         @JvmStatic
         @BindingAdapter("poster_path_big")
         fun loadImageBig(listImage: ImageView, url:String?){
             if (!url.isNullOrEmpty())
                Glide.with(listImage)
                     .load("https://www.themoviedb.org/t/p/w355_and_h200_multi_faces/" + url)
                     .placeholder(R.drawable.ic_launcher_foreground)
                     .error(R.drawable.ic_launcher_foreground)
                     .fallback(R.drawable.ic_launcher_foreground)
                     .into(listImage)

         }
     }
}