package com.vm.vktesttask.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vm.vktesttask.data.ApiFactoryValues
import com.vm.vktesttask.data.repository.ValuesRepositoryImpl
import com.vm.vktesttask.domain.usecase.GetDailyValuesUseCase
import com.vm.vktesttask.domain.model.Values
import kotlinx.coroutines.launch
import kotlin.math.roundToLong

class ValuesViewModel(application: Application) : AndroidViewModel(application) {
    private val valuesRepository = ValuesRepositoryImpl(ApiFactoryValues.valuesApiService)
    private val getDailyValuesUseCase = GetDailyValuesUseCase(valuesRepository)

    private val _valuesList = MutableLiveData<List<Values>>()
    val valuesList: LiveData<List<Values>> = _valuesList

    private val _currency = MutableLiveData("0,00")
    val currency: LiveData<String> = _currency

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            val values = fixNominal(getDailyValuesUseCase())
            _valuesList.postValue(values)
        }
    }

    private fun fixNominal(values: List<Values>): List<Values> {
        return values.map {
            if (it.nominal != 1) {
                it.copy(value = it.value / it.nominal)
            } else {
                it
            }
        }
    }

    fun convertCurrency(from: String, to: String, amount: Double){
        val fromCurrency = _valuesList.value?.find { it.charCode==from }
        val toCurrency = _valuesList.value?.find { it.charCode==to }
        if (fromCurrency != null && (toCurrency != null)) {
            _currency.value = String.format("%.2f",(amount*fromCurrency.value)/toCurrency.value)
        }
    }
}