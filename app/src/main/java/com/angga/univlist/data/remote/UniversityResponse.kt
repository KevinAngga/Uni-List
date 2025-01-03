package com.angga.univlist.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class UniversityResponse(
    val universities : List<UniversitySerializable> = listOf()
)
