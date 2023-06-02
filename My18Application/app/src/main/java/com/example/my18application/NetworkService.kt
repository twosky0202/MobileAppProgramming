package com.example.my18application

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    // http://apis.data.go.kr/B553748/CertImgListService/
    // getCertImgListService?
    @GET("getCertImgListService")
    fun getList(
        // query안의 내용은 사용하려는 데이터 항목명과 똑같이
        @Query("prdlstNm") q:String,
        @Query("ServiceKey") apikey:String,
        @Query("pageNo") page:Long,
        @Query("numOfRows") pageSize:Int,
        @Query("returnType") returnType:String
        ) : Call<MyModel>
}