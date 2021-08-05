package com.islam.task.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.internet.ConnectivityInterCeptor
import com.islam.task.data.network.internet.ConnectivityInterCeptorImpl
import com.islam.task.data.repositories.DefaultPaymentRepository
import com.islam.task.data.repositories.PaymentRepository
import com.islam.task.generalUtils.Utils
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultPaymentRepository(api: MyTaskApi) =
        DefaultPaymentRepository(api) as PaymentRepository

    @Singleton
    @Provides
    fun provideConnectivityInterCeptor(@ApplicationContext context: Context): ConnectivityInterCeptor {
        return ConnectivityInterCeptorImpl(context)
    }

    @Singleton
    @Provides
    fun provideAPI(connectivityInterCeptor: ConnectivityInterCeptor): MyTaskApi {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okkHttpclient = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(connectivityInterCeptor)
            .addInterceptor(interceptor)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .client(okkHttpclient)
            .baseUrl(Utils.getUrl())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MyTaskApi::class.java)
    }

}

















