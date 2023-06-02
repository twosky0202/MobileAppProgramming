package com.example.my13application

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my13application.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val bgColor:String? = sharedPreferences.getString("bg_color", "")
        binding.mainRecyclerView.setBackgroundColor(Color.parseColor(bgColor))

        val requestlauncher:ActivityResultLauncher<Intent>
            = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                it.data!!.getStringExtra("result")?.let{
                    datas?.add(it)
                    adapter.notifyDataSetChanged()

                    var db:SQLiteDatabase = DBHelper(this).writableDatabase
                    // SQL : insert into todo_tbl (todo) values (it...)
                    db.execSQL("insert into todo_tbl (todo) values (?)", arrayOf<String>(it))
                    db.close()
                }
        }

        // +버튼 눌렀을 때
        binding.mainFab.setOnClickListener {
            // 인텐트를 시스템에 전달
            var intent = Intent(this, AddActivity::class.java)
            intent.putExtra("data", "My todo List")
//            startActivity(intent) // 사후 처리가 필요 없을 때
//            startActivityForResult()
//            ActivityResultLauncher
            requestlauncher.launch(intent)

        }

        /*
        datas = savedInstanceState?.let {
            it.getStringArrayList("datas")?.toMutableList()
        }?:let{ mutableListOf<String>() }
        */
        // DB 읽기
        datas = mutableListOf<String>()
        val db:SQLiteDatabase = DBHelper(this).readableDatabase
        val cursor: Cursor = db.rawQuery("select * from todo_tbl", null)
        while (cursor.moveToNext()){
            datas?.add(cursor.getString(1))
        }
        db.close()

        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager = layoutManager
        adapter = MyAdapter(datas)
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onResume() {
        super.onResume()

        val bgColor:String? = sharedPreferences.getString("bg_color", "")
        binding.mainRecyclerView.setBackgroundColor(Color.parseColor(bgColor))

        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val file: File = File(filesDir, "todo_list.txt")
        when(item.itemId) {
            R.id.menu_save_file -> {
                val writeStream:OutputStreamWriter = file.writer()
                writeStream.write("hello android!!\n")
                for(i in datas!!.indices)
                    writeStream.write(datas!![i] + "\n")
                writeStream.flush() // 직접적으로 파일에 작성
                return true
            }
            R.id.menu_read_file -> {
                val readStream:BufferedReader = file.reader().buffered() // InputStreamReader
                readStream.forEachLine {
                    Log.d("mobileApp", "$it")
                }
                return true
            }
            R.id.menu_main_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("datas", ArrayList(datas))
    }
}
/*
app->new->activity->empty activity->이름설정
MinActivity +버튼 눌렀을 때
인텐트를 시스템에 전달

콘텐츠 프로바이더
xml new file_paths, paths
androidManifest provider, user-permission 추가
 */