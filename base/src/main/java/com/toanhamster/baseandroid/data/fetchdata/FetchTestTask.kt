package com.toanhamster.baseandroid.data.fetchdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.toan_itc.core.architecture.*
import com.toanhamster.baseandroid.data.api.ApiService
import com.toanhamster.baseandroid.data.model.query.EmailQuery
import com.toanhamster.baseandroid.utils.returnBody

/**
 * Created by ToanDev on 24/2/19.
 * Email:Huynhvantoan.itc@gmail.com
 */

class FetchTestTask
internal constructor(private val email: String, private val apiService: ApiService) : Runnable {
    private val liveData = MutableLiveData<Resource<Int>>()
    private val messageError = "Get data error"

    override fun run() {
        val newValue = try {
            val value = Gson().toJson(EmailQuery(email))
            val response = apiService.getTest(returnBody(value)).execute()
            val apiResponse = ApiResponse.create(response)
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    when {
                        response.body()?.result?.size!! > 0 -> Resource.success(if(response.body()?.result?.get(0)?.activated == true) 1 else 0 )
                        response.code() == 204 -> Resource.success(2)
                        else -> Resource.error(messageError, 0)
                    }
                }
                is ApiEmptyResponse -> Resource.error(messageError, 0)
                is ApiErrorResponse -> Resource.error(messageError, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.message.toString(), 0)
        }
        liveData.postValue(newValue)
    }

    internal fun getLiveData(): LiveData<Resource<Int>> = liveData

}
