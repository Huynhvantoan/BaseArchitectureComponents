package com.toanhamster.baseandroid.data.api

import com.toanhamster.baseandroid.data.model.test.TestModel
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 * REST API access points
 */

interface ApiService {

    @GET
    fun getActive(@Url link: String): Call<ResponseBody>

    @POST("sc/device/active/get")
    fun getTest(@Body body: RequestBody): Call<JsonArray<TestModel>>
}
