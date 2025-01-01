package com.angga.univlist.ui.domain.model

data class University(
    val uniName: String = "",
    val countryCode: String = "",
    val webPages: List<String> = listOf(),
)
