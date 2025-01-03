package com.angga.univlist.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.angga.univlist.domain.model.University
import com.angga.univlist.domain.repository.UniversityRepository
import com.angga.univlist.uniList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UniversityRepositoryImplFake : UniversityRepository{
    override fun getUniversitiesList(): Flow<PagingData<University>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                PagingSourceFake(uniList)
            }
        ).flow
    }

    override fun getUniversitiesByName(universityName: String): Flow<PagingData<University>> {
        return flow {
            val filteredData = if (universityName.isEmpty()) {
                uniList
            } else {
                uniList.filter { it.uniName.contains(universityName, ignoreCase = true) }
            }
            emit(PagingData.from(filteredData))
        }
    }
}