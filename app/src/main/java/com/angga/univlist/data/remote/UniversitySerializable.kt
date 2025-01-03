package com.angga.univlist.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversitySerializable(
    @SerialName("web_pages")
    val webPages: List<String> = listOf(),
    @SerialName("name")
    val uniName : String = "",
    @SerialName("alpha_two_code")
    val countryCode : String
)
