package com.example.my07application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/*
1. LinearLayout 선형으로 배치
여백채우기
layout_weight="가중치" ex. 1, 3이면 각각 1/4, 3/4만큼 나누어 차지

뷰 정렬하기
gravity="right|bottom" 콘텐츠 오른쪽 아래
layout_gravity="center_vertical" 뷰 자체 정렬. 그러나 linearLayout 같은 레이아웃에선 먹지 않음. LinearLayout 에 gravity="center"로 설정해야 함.

2. RelativeLayout 상대 위치로 배치
layout_above : 기준 뷰의 위쪽
layout_below: 기준 뷰의 아래쪽
layout_toLeftOf: 기준 뷰의 왼쪽
layout_toRightOf: 기준 뷰의 오른쪽
-> 예시는 ch07layout activity_main 가서 봐라

맞춤 정렬 align
layout_alignTop(Bottom, Left, Right, BaseLine): 기준 뷰와 위쪽(아래쪽, 왼쪽, 오른쪽, 텍스트 기준선)을 맞춤



3. FrameLayout
똑같은 위치에 여러 뷰를 겹쳐놓고 어느 순간에 하나의 뷰만 출력할 떄 사용. 대부분 visivility속성과 함께 사용

4. GridLayout 행과 열로 구성된 테이블 화면
orientation="horizontal|vertical" 가로|세로
rowCount="3" 세로로 나열할 뷰 개수
columnCount="3" 가로로 나열할 뷰 개수

특정뷰 위치 조정(인덱스 0부터 시작)
layout_row : 세로방향 인덱스
layout_column : 가로방향 인덱스
상위 레이아웃 기준 맞춤 정렬
layout_alignParentTop(Bottom, Left, Right): 부모의 위쪽
layout_centerHorizontal(Vertical, InParent): 부모의 가로 방향(세로 방향, 가로.세로)중앙
특정뷰 크기 확장
layout_gravity

열이나 행 결합
layout_columnSpan : 가로로 열 병합
layout_rowSpan : 세로로 행 병합

5. ConstraintLayout 계층 구조로 배치
androidx 제공 라이브러리
*/