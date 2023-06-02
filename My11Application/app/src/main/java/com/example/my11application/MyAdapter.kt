package com.example.my11application

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.my11application.databinding.ItemRecyclerviewBinding

// 뷰홀더 준비
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)
// 어댑터 준비 -> 뷰 홀더의 뷰에 데이터를 출력해 각 항목을 만들어주는 역할. RecyclerView.Adapter상속
class MyAdapter(val datas:MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // 항목 개수 판단 자동 호출
    override fun getItemCount(): Int { 
        return datas.size
    }
    // 항목의 뷰를 가지는 뷰 홀더 준비 자동 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    // 뷰 홀더의 뷰에 데이터 출력 자동 호출
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.itemData.text = datas[position]
    }
}

// 아이템 데커레이션 -> 리사이클러뷰 꾸밀때
class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
    // 항목이 모두 배치된 후에 호출, onDraw(): 항목이 배치되기 전에 호출, getItemOffSets(): 개별항목을 꾸밀때 호출
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //이미지 크기 계산
        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val drW = dr?.intrinsicWidth
        val drH = dr?.intrinsicHeight
        // 이미지가 그려질 위치 계산
        val left = parent.width/2 - drW?.div(2) as Int //parent.width: 뷰크기
        val top = parent.height/2 - drH?.div(2) as Int
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), left.toFloat(), top.toFloat(), null)

    }
}