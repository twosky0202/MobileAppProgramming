package com.example.my18application

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.my18application.databinding.ActivityAddBinding
import java.io.File

import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding
    lateinit var filePath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode === android.app.Activity.RESULT_OK) {
                // 이미지를 ImageView에 보이기
                Glide
                    .with(applicationContext)
                    .load(it.data?.data) // 이미지 불러오기
                    .apply(RequestOptions().override(250, 200)) // 이미지 사이즈 규격화
                    .centerCrop() // 이미지가 사이즈보다 큰 경우 센터를 기준으로 자름
                    .into(binding.addImageView) // 이미지뷰에 적용
                // 이미지의 주소 저장
                val cursor = contentResolver.query(it.data?.data as Uri, arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null)
                cursor?.moveToFirst().let {
                    filePath = cursor?.getString(0) as String
                    Log.d("mobileApp", "${filePath}")
                }
            }
        }
        binding.btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK) // 갤러리에서 선택하는 순간
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "images/*") // 갤러리에 있는 이미지를 불러오는 방법
            intent.type="images/*"
            requestLauncher.launch(intent)
        }

        binding.btnSave.setOnClickListener {
            if(binding.addEditView.text.isNotEmpty() && binding.addImageView.drawable !== null){
                // firestore 저장
                saveStore()
            } else{
                Toast.makeText(this, "내용을 입력해주세요..", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
    fun dateToString(date:Date):String{
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm")
        return format.format(date)

    }
    fun saveStore(){
        val data = mapOf(
            "email" to MyApplication.email,
            "content" to binding.addEditView.text.toString(),
            "date" to dateToString(Date()),
        )
        MyApplication.db.collection("news")
            .add(data)
            .addOnSuccessListener {
                Log.d("mobileApp", "data firestore save ok")
                // uploadImage(it.id)
            }
            .addOnFailureListener {
                Log.d("mobileApp", "data firestore save error - ${it.toString()}")
            }
    }
    fun uploadImage(docId:String){
        val storage = MyApplication.storage
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${docId}.jpg") // 스토리지에 접근. 저장 경로 설정
        val file = Uri.fromFile(File(filePath))
        imageRef.putFile(file) // file에 있는 내용을 이미지 레퍼런스를 이용해서 넣겠다.
            .addOnSuccessListener {
                Log.d("mobileApp", "imageRef.putFile(file) - addOnSuccessListener")
                finish()
            }
            .addOnFailureListener {
                Log.d("mobileApp", "imageRef.putFile(file) - addOnFailureListener - ${it.toString()}")
            }
    }
}