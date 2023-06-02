package com.example.my18application

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.my18application.databinding.ItemRetrofitBinding

class MyRetrofitViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

class MyRetrofitAdapter(val context: Context, val datas: MutableList<MyItem>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyRetrofitViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyRetrofitViewHolder).binding

        //add......................................
        val model = datas!![position]
        binding.itemName.text = model.item.prdlstNm
        binding.itemCapacity.text = model.item.capacity
        binding.itemRaw.text = model.item.rawmtrl

        Glide.with(context)
            .load(model.item.imgurl1)
            .into(binding.itemImage2)
    }
}