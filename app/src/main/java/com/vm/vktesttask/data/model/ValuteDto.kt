package com.vm.vktesttask.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ValuteDto(

    @SerializedName("CharCode")
    @Expose
    val charCode: String,

    @SerializedName("Name")
    @Expose
    val name: String,

    @SerializedName("Value")
    @Expose
    val value: Double,

    @SerializedName("Nominal")
    @Expose
    val nominal: Int,
)