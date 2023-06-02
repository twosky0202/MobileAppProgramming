package com.example.ch08event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.ch08event.databinding.ActivityMainBinding
import java.util.ResourceBundle

class MainActivity : AppCompatActivity() {
    // 멈춘 시각 저장하는 속성
    var pauseTime = 0L

    // 뒤로가기 버튼을 누른 시각을 저장하는 속성
    var initTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setTextColor(ResourcesCompat.getColor(resources,R.color.textColor,null))
        binding.startBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                binding.myChrono.base = SystemClock.elapsedRealtime() + pauseTime
                binding.myChrono.start()
                // 버튼 표시 여부
                binding.startBtn.isEnabled = false
                binding.stopBtn.isEnabled = true
                binding.resetBtn.isEnabled = true
            }
        })

        binding.stopBtn.setOnClickListener {
            pauseTime = binding.myChrono.base - SystemClock.elapsedRealtime()
            binding.myChrono.stop()
            binding.startBtn.isEnabled = true
            binding.stopBtn.isEnabled = false
            binding.resetBtn.isEnabled = true
        }

        binding.resetBtn.setOnClickListener {
            pauseTime = 0L
            binding.myChrono.base = SystemClock.elapsedRealtime()
            binding.myChrono.stop()
            binding.startBtn.isEnabled = true
            binding.stopBtn.isEnabled = false
            binding.resetBtn.isEnabled = false
        }
    }

    // 뒤로 가기 버튼 이벤트 핸들러
    override fun onBackPressed() {
        // 뒤로가기 버튼을 처음 눌렀거나 누른 지 3초가 지났을 때 처리
        if(SystemClock.elapsedRealtime()-initTime > 3000){ // 3sec
            Log.d("mobileApp", "종료하려면 한 번 더 누르세요!")
            initTime = SystemClock.elapsedRealtime()
        }
    }
}

/*
build.gradle 파일 viewBinding 설정
res->drawable->round_button.xml 작성, values->colors.xml textColor 추가, styles.xml 추가
activity_main.xml 작성

*/