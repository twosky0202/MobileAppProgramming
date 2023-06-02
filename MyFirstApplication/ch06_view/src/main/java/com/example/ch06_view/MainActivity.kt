package com.example.ch06_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // id값으로 뷰 객체 획득
//        val textView1: TextView = findViewById(R.id.textView)
//        val textView1 = findViewById<TextView>(R.id.textView)
//        => viewBinding으로 한번에! -> 08장 app에 설명
    }
}

/*
visibility: visible(화면에출력), invisible(출력x, 자리차지), gone(출력x, 자리차지x)

**앞에 다 android 붙이기!!
TextView
text="@string/hello"
textColor="#FF0000" (16진수 rgb)
textSize="20sp" (단위 dp, px, sp)
textStyle="bold" (italic, normal)
autoLink="web|email|phone" 출력할 문자열 분석해 자동 링크 추가
maxLine="3" 3줄까지나오게
ellipsize="end" 문자열 더 표시 줄임표 추가(+middle, start)

ImageView
src="@drawable/image"
maxWidth, maxHeight -> adjustViewBounds="true"와 같이 사용해야함!

RadioButton RadioGroup 합께 사용. 그룹으로 묶은 것 중 하나만 선택

EditText
lines="3" 처음부터 3줄 입력 크기로, 화면상 더 늘어나지않음, 스크롤되어 여러줄 입력가능하긴함
###maxLines는 처음에는 한줄 입력크기, 엔터시 화면상 3줄까지늘어나며 더 늘어나지 않음, 스크롤되어 여러줄 입력가능하긴함
inputType="" 올라오는 키보드 지정
    ->none(모든문자 가능, 줄바꿈 가능), text(문자열 한줄), textCapCharacters(대문자), textCapWords(각단어첫글자 자동대문자),
    textCapSentences(각문단첫글자 자동대문자), textMuliLine(여러줄입력), textNoSuggestions(단어입력시 추천단어x), textUri(URL 입력모드),
    textEmailAddress(이메일주소입력), textPassword(비밀번호. 입력문자 점으로 표시, 영문자.숫자.특수키만 표시), textVisiblePassword(비밀번호. 입력문자표시)
    number(숫자 입력), numberSigned(숫자, 부호키 - 입력), numberDecimal(숫자, 소숫점 가능), numberPassword(숫자키만 비밀번호. 입력문자 점),
    phone(전화번호 입력)
*/