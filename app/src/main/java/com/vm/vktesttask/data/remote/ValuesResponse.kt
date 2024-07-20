package com.vm.vktesttask.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vm.vktesttask.data.model.ValuteDto

data class ValuesResponse(
    @SerializedName("Valute")
    @Expose
    val valute: Map<String, ValuteDto>
)