package com.example.ch08resource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ch08resource.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/*
리소스 종류
animator, anim, color, drawable(이미지), mipmap, layout, menu, raw, values(값. 문자열, 색상, 크기, 스타일, 배열 등), xml, font
style등록 -> 08 style:~이거 참고
속성이 다른 같은 파일명 -> name, id 다 똑같아야함
local, orientation->landscape
*/