package com.islam.task.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.internet.ConnectivityInterCeptor
import com.islam.task.data.network.internet.ConnectivityInterCeptorImpl
import com.islam.task.data.repositories.DefaultPaymentRepository
import com.islam.task.data.repositories.PaymentRepository
import com.islam.task.generalUtils.Utils
import com.islam.task.repositories.FakePaymentMethodRepoForUI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object AppModuleTest {

    @Singleton
    @Provides
    fun provideFakePaymentMethodRepoForUI() =
        FakePaymentMethodRepoForUI() as PaymentRepository

}

















