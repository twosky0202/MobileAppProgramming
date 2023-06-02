package com.example.my18application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.my18application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    lateinit var boardFragment: BoardFragment
    var mode = "volley"
    var authMenuItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyFragment = VolleyFragment()
        retrofitFragment = RetrofitFragment()
        boardFragment = BoardFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, volleyFragment)
            .commit() // replace 부분 실행
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_content, retrofitFragment)
            .hide(retrofitFragment) // retrofitFragment이 보여지지 않게끔
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_content, boardFragment)
            .hide(boardFragment) // retrofitFragment이 보여지지 않게끔
            .commit()
        supportActionBar?.title="Volley Test"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        
        authMenuItem = menu!!.findItem(R.id.menu_auth)
        if(MyApplication.checkAuth()){
            authMenuItem!!.title = "${MyApplication.email}님"
        }
        else{
            authMenuItem!!.title = "인증"
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStart() {
        // Intent에서 finish() 돌아올 때 실행
        // onCreate -> onStart -> onCreateOptionsMenu
        super.onStart()

        if(authMenuItem != null){
            if(MyApplication.checkAuth()){
                authMenuItem!!.title = "${MyApplication.email}님"
            }
            else {
                authMenuItem!!.title = "인증"
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_volley && mode !== "volley"){ // volley가 아닌데 volley를 누르면
            supportFragmentManager.beginTransaction()
                .show(volleyFragment) // volleyFragment로 바꾸고
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(retrofitFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(boardFragment)
                .commit()

            mode="volley" // 현재 상태를 volley로 바꾸겠다.
            supportActionBar?.title="Volley Test"
        }
        else if(item.itemId === R.id.menu_retrofit && mode !== "retrofit"){ // retrofit이 아닌데 retrofit을 누르면
            supportFragmentManager.beginTransaction()
                .show(retrofitFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(volleyFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(boardFragment)
                .commit()

            mode="retrofit"
            supportActionBar?.title="Retrofit Test"
        }
        else if(item.itemId === R.id.menu_board && mode !== "board"){
            supportFragmentManager.beginTransaction()
                .show(boardFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(volleyFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(retrofitFragment)
                .commit()

            mode="board"
            supportActionBar?.title="Board Test"
        }
        else if(item.itemId === R.id.menu_auth){
            val intent = Intent(this, AuthActivity::class.java)
            if(authMenuItem!!.title!!.equals("인증")){
                intent.putExtra("data", "logout")
            }
            else{ // 이메일, 구글계정 표시가 되고 있다.
                intent.putExtra("data", "login")
            }
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}