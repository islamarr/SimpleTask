package com.islam.task

import android.app.Application
import com.islam.task.data.network.MyTaskApi
import com.islam.task.data.network.internet.ConnectivityInterCeptorImpl
import com.islam.task.data.repositories.DefaultPaymentRepository
import com.islam.task.ui.paymentMethods.PaymentViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidCoreModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class PayoneerApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidCoreModule(this@PayoneerApp))

        bind() from singleton { MyTaskApi(instance()) }
        bind() from singleton { ConnectivityInterCeptorImpl(instance()) }
        bind() from singleton { DefaultPaymentRepository(instance()) }

        bind() from provider { PaymentViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}