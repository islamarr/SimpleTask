package com.islam.task.data.repositories

import android.util.Log
import com.islam.task.data.Resource
import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.response.MainResponse
import com.islam.task.generalUtils.ApiException
import com.islam.task.generalUtils.NoInternetException
import javax.inject.Inject


class DefaultPaymentRepository @Inject constructor(private val api: MyTaskApi) : PaymentRepository {

    override suspend fun getPaymentMethods(): Resource<MainResponse> {
        return try {
            val response = api.getPaymentMethods()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("Something went wrong, try again!")
            } else {
                Resource.Error("Something went wrong, try again!")
            }
        } catch (e: ApiException) {
            Log.e(TAG, e.toString())
            Resource.Error("Something went wrong, try again!")
        } catch (ne: NoInternetException) {
            Log.e(TAG, ne.toString())
            Resource.Error("Make sure you have an active Internet connection!")
        }
    }

    companion object {
        private const val TAG = "DefaultPaymentRepositor"
    }

}