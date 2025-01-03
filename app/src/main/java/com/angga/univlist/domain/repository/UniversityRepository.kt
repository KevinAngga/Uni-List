package com.angga.univlist.domain.repository

import androidx.paging.PagingData
import com.angga.univlist.domain.model.University
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun getUniversitiesList() : Flow<PagingData<University>>
    fun getUniversitiesByName(universityName : String) : Flow<PagingData<University>>
}