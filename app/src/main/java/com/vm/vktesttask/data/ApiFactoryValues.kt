package com.vm.vktesttask.data

import com.vm.vktesttask.data.remote.ValuesApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactoryValues {
    private const val BASE_URL = "https://www.cbr-xml-daily.ru/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val valuesApiService: ValuesApiService by lazy{
        retrofit.create(ValuesApiService::class.java)
    }
}