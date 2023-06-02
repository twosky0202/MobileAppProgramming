package com.example.my08application

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import com.example.my08application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩
        // 그래들 파일 android에
        //    viewBinding{
        //        enabled = true
        //    } 추가하고 그래들파일 위쪽에 뜨는거 눌러줘야함함        // 바인딩 객체 획득
        val binding = ActivityMainBinding.inflate(layoutInflater)
        // 액티비티 화면 출력
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        // 뷰 객체 이용. 뷰 이벤트
        //      이벤트소스.리스너(이벤트핸들러 ~          View에 정의되어있음.
        binding.helloBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("mobileApp", "helloBtn_CLICK")
            }
        })
        binding.helloBtn.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                Log.d("mobileApp", "helloBtn_LONG_CLICK")
                return true
            }
        })
    }

    // 터치 이벤트 처리
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> { // 화면을 손가락으로 누른 순간
                Log.d("mobileApp", "MotionEvent.ACTION_DOWN")
                // {event.x} 이벤트 발생한 뷰의 x좌표, {event.rawX} : 화면의 x좌표
            }
            MotionEvent.ACTION_UP -> { // 화면을 손가락으로 떼는 순간
                Log.d("mobileApp", "MotionEvent.ACTION_UP")
            }
            // ACTION_MOVE : 화면을 손가락으로 누른채로 이동하는 순간
       }
        return super.onTouchEvent(event)
    }

    // 키 이벤트
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean { // 키를 누른 순간, onKeyUp 키를 떼는 순간, onKeyLongPress 키를 오래 누르는 순간
        when(keyCode){
            KeyEvent.KEYCODE_0 -> {
                Log.d("mobileApp", "KeyEvent.KEYCODE_0")
            }
            KeyEvent.KEYCODE_A -> {
                Log.d("mobileApp", "KeyEvent.KEYCODE_A")
            }
//            KeyEvent.KEYCODE_BACK -> { // 뒤로가기
//                Log.d("mobileApp", "KeyEvent.KEYCODE_BACK")
//            }
//            KEYCODE_VOLUME_UP(DOWN): 볼륨 업(다운)
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() { // 뒤로가기. 위의 KEYCODE_BACK이랑 똑같음
        Log.d("mobileApp", "KeyEvent.KEYCODE_BACK")
    }
}