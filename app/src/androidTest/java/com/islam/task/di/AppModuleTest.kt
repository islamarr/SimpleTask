package com.islam.task.di

import com.islam.task.data.repositories.PaymentRepository
import com.islam.task.repositories.FakePaymentMethodRepoForUI
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
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

















