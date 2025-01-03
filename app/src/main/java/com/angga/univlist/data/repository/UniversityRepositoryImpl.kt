package com.angga.univlist.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.angga.univlist.data.local.UniversityDatabase
import com.angga.univlist.data.pagination.UniversityRemoteMediator
import com.angga.univlist.data.remote.mapper.toUniversity
import com.angga.univlist.domain.model.University
import com.angga.univlist.domain.repository.UniversityRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UniversityRepositoryImpl(
    private val universityDatabase: UniversityDatabase,
    private val httpClient: HttpClient,
) : UniversityRepository{
    @OptIn(ExperimentalPagingApi::class)
    override fun getUniversitiesList(): Flow<PagingData<University>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = UniversityRemoteMediator(
                httpClient = httpClient,
                universityDatabase = universityDatabase
            ),
            pagingSourceFactory = { universityDatabase.universityDao.getAll() }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUniversity()
            }
        }
    }

    override fun getUniversitiesByName(universityName: String): Flow<PagingData<University>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { universityDatabase.universityDao.getAll() }
        ).flow.map { pagingData ->
            if (universityName.isEmpty()) {
                pagingData.map {
                    it.toUniversity()
                }
            } else {
                pagingData.filter { it.uniName.contains(universityName) }.map {
                    it.toUniversity()
                }
            }
        }
    }
}