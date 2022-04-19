package com.mobillium.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobillium.myapplication.databinding.ItemAltKategoriBinding
import com.mobillium.myapplication.databinding.ItemPlaymovieBinding

class PagerAdapter: RecyclerView.Adapter<PagerAdapter.MyViewHolder>(){
    var items =ArrayList<RecyclerData>()
    lateinit var nowPlayingCard:CardView
    private lateinit var listener:IListeler

    fun setDataList(data:ArrayList<RecyclerData>, listener:IListeler){
        this.items=data
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaymovieBinding.inflate(layoutInflater)
        return MyViewHolder(binding)

    }

    override fun getItemCount()=items.size

    class MyViewHolder(val binding: ItemPlaymovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data:RecyclerData){
            binding.nowRecyclerData = data
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

    override fun onBindViewHolder(holder: PagerAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
        nowPlayingCard=holder.itemView.findViewById(R.id.nowPlayingCard)

        holder.binding.nowPlayingCard.setOnClickListener {
            println("tıklandı")
            listener.onClick(position)
        }

    }


}