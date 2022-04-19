package com.mobillium.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobillium.myapplication.databinding.ItemAltKategoriBinding

class RecyclerViewAdapter():RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){
   lateinit var  duzenle :ImageView
    lateinit var listener:IListeler

    var items =ArrayList<RecyclerData>()


    fun setDataList(data:ArrayList<RecyclerData>, listener:IListeler){
        this.items=data
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =ItemAltKategoriBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {

       holder.bind(items[position])
        duzenle=holder.itemView.findViewById(R.id.tikla)
        holder.binding.tikla. setOnClickListener {
            println("tıklandı")
            listener.onClick(position)
        }

    }

    override fun getItemCount()=items.size

    class MyViewHolder(val binding:ItemAltKategoriBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data:RecyclerData){
            binding.recyclerData = data
            binding.executePendingBindings()
        }
    }

    companion object{
        @JvmStatic
        @BindingAdapter("poster_path")
        fun loadImage(listImage:ImageView,url:String){
            Glide.with(listImage)
                .load("https://www.themoviedb.org/t/p/w355_and_h200_multi_faces/" + url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(listImage)

        }
    }
}