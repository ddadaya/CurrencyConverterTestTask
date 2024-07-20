package com.vm.vktesttask.domain.usecase

import com.vm.vktesttask.domain.model.Values
import com.vm.vktesttask.domain.repository.ValuesRepository

class GetDailyValuesUseCase(private val valuesRepository: ValuesRepository) {
    suspend operator fun invoke(): List<Values> {
        return valuesRepository.getDailyValues()
    }
}