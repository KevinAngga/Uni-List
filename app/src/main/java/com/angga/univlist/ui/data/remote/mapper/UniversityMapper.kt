package com.angga.univlist.ui.data.remote.mapper

import com.angga.univlist.ui.data.local.entity.UniversityEntity
import com.angga.univlist.ui.data.remote.UniversitySerializable
import com.angga.univlist.ui.domain.model.University

fun UniversitySerializable.toUniversityEntity() : UniversityEntity {
    return UniversityEntity(
        webPages = webPages,
        uniName = uniName,
        countryCode = countryCode
    )
}

fun UniversityEntity.toUniversity() : University {
    return University(
        webPages = webPages,
        uniName = uniName,
        countryCode = countryCode
    )
}