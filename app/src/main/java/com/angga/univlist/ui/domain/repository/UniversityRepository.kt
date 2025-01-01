package com.angga.univlist.ui.domain.repository

import androidx.paging.PagingData
import com.angga.univlist.ui.domain.model.University
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun getUniversitiesList() : Flow<PagingData<University>>
    fun getUniversitiesByName(universityName : String) : Flow<PagingData<University>>
}