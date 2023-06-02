package com.example.my11application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.my11application.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.android.material.tabs.TabLayoutMediator

// 1. appcompatactivity - api 호환성 해결, 액션바, 툴바
class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    lateinit var toolbar : ActionBarDrawerToggle

    // 프래그먼트 어댑터 이용
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        val fragments : List<Fragment>
        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 액션바의 내용을 툴바에 등록
        setSupportActionBar(binding.toolbar)

        // 뷰 페이저2 어댑터에 적용
        binding.viewpager2.adapter = MyFragmentAdapter(this)/*어댑터 연결*/

        // ActionBarDrawerToggle 버튼 적용(햄버거)
        toolbar = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.syncState()

        // 항목 선택 이벤트 핸들러
        binding.mainDrawer.setNavigationItemSelectedListener(this)

        // 텝레이아웃과 뷰페이저2 연동
        TabLayoutMediator(binding.tabs, binding.viewpager2) {
            tab, position -> tab.text = "TAB ${position+1}"
        }.attach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> {Log.d("mobileApp", "네비게이션뷰 메뉴 1")}
            R.id.menu2 -> {Log.d("mobileApp", "네비게이션뷰 메뉴 2")}
            R.id.menu3 -> {Log.d("mobileApp", "네비게이션뷰 메뉴 3")}
            R.id.menu4 -> {Log.d("mobileApp", "네비게이션뷰 메뉴 4")}
        }
        return true
    }

    // 액션바->메뉴 구성. 메뉴를 사용자가 선택했을 때 이벤트 처리
    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // 액티비티가 실행되면서 처음에 한 번만 호출
        menuInflater.inflate(R.menu.menu_actionbar, menu)

        val menuItem = menu?.findItem(R.id.menu_search) // MenuItem 식별값 주어 얻는다.
        // 검색 관련 기능 구현
        val searchView = menuItem?.actionView as SearchView // 등록된 액션뷰는 actionView 속성으로 얻는다.
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            // 키보드 검색 버튼 클릭 순간 이벤트
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("mobileApp", "검색어 : ${query}")
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toolbar.onOptionsItemSelected(item)){
            return true
        }
        when(item.itemId) {
            R.id.menu1 -> {
                Log.d("mobileApp", "menu1")
            }
            R.id.menu2 -> {}
            R.id.menu_search -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}
/*
메뉴구성

리소스로 구성->menu_actionbar-> app:showAsAction
never(기본): 항상 오버플로, ifRoom:만약 액션바에 공간이 있다면 액션 아이템으로, 없다면 오버플로, always: 항상 액션 아이템
search-> 액션뷰
액션바의 내용을 툴바에 적용
프래그먼트 구현

리사이클러뷰: ViewHolder(필수), Adapter(필수), LayoutManager(필수), ItemDecoration(옵션)
리사이클러뷰 myadapter 구현
리사이클러뷰 출력 oneFragment
아이템 데커레이션 myadapter
activity_main 뷰페이지2 추가-> MainActivity 뷰 페이저2 어댑터에 적용
프래그먼트 어댑터 이용
드로어레이아웃 activity_main추가 -> 드로어 메뉴 토글 버튼 적용


실습
뷰바인딩
프래그먼트 생성, 작성
메뉴 리소스 만들기 res->new->android resource directory->resource type:menu , menu 디렉토리 -> new->menu resource file
activity_main.xml 등록
MainActivity 파일 작성

appbarlayout 안에 toolbar 추가
탭레이아웃 등록
텝레이아웃과 뷰페이저2 연동
내이게이션뷰 등록
항목 선택 이벤트 핸들러
fragment_one.xml floatingActionButton 추가
OneFragment 확장 플로팅 액션 버튼 조절
* */