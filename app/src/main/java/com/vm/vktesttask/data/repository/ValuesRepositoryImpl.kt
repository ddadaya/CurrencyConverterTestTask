package com.vm.vktesttask.data.repository

import com.vm.vktesttask.data.remote.ValuesApiService
import com.vm.vktesttask.domain.model.Values
import com.vm.vktesttask.domain.repository.ValuesRepository

class ValuesRepositoryImpl(private val valuesApiService: ValuesApiService) : ValuesRepository {

    override suspend fun getDailyValues(): List<Values> {
        return valuesApiService.getDailyValues().valute.values.map {
            Values(charCode = it.charCode, name = it.name, value = it.value, nominal = it.nominal)
        }
    }
}