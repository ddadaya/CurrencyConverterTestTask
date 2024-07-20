package com.vm.vktesttask.domain.repository

import com.vm.vktesttask.domain.model.Values

interface ValuesRepository {
    suspend fun getDailyValues(): List<Values>
}