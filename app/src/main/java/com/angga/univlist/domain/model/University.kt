package com.angga.univlist.domain.model

data class University(
    val uniName: String = "",
    val countryCode: String = "",
    val webPages: List<String> = listOf(),
)
