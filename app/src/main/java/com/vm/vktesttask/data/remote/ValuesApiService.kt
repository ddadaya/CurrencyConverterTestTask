package com.vm.vktesttask.data.remote

import retrofit2.http.GET

interface ValuesApiService {
    @GET("daily_json.js")
    suspend fun getDailyValues(): ValuesResponse
}