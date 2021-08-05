package com.islam.task.ui.paymentMethods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.task.data.Resource
import com.islam.task.data.network.response.MainResponse
import com.islam.task.data.repositories.PaymentRepository
import com.islam.task.generalUtils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val repository: PaymentRepository) :
    ViewModel() {

    private val _methods = MutableLiveData<Event<Resource<MainResponse>>>()
    val methods: LiveData<Event<Resource<MainResponse>>> = _methods

    fun getPaymentMethods() {

        viewModelScope.launch {
            val response = repository.getPaymentMethods()
            _methods.value = Event(response)
        }

    }

}