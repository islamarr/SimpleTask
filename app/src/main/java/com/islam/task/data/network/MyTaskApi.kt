package com.islam.task.data.network

import com.islam.task.data.network.response.MainResponse
import retrofit2.Response
import retrofit2.http.GET

interface MyTaskApi {

    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    suspend fun getPaymentMethods(): Response<MainResponse>

}

