package com.angga.univlist.ui.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class UniversityResponse(
    val universities : List<UniversitySerializable> = listOf()
)
