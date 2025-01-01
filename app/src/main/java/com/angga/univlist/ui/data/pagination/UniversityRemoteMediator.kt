package com.angga.univlist.ui.data.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.angga.univlist.ui.data.INDONESIA_UNI
import com.angga.univlist.ui.data.LIMIT
import com.angga.univlist.ui.data.OFFSET
import com.angga.univlist.ui.data.local.UniversityDatabase
import com.angga.univlist.ui.data.local.entity.UniversityEntity
import com.angga.univlist.ui.data.local.entity.UniversityRemoteKeysEntity
import com.angga.univlist.ui.data.remote.UniversitySerializable
import com.angga.univlist.ui.data.remote.mapper.toUniversityEntity
import com.angga.univlist.ui.data.remote.utils.get
import com.angga.univlist.ui.domain.utils.map
import io.ktor.client.HttpClient
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

@OptIn(ExperimentalPagingApi::class)
class UniversityRemoteMediator(
    private val universityDatabase: UniversityDatabase,
    private val httpClient: HttpClient
) : RemoteMediator<Int, UniversityEntity>() {
    private val remoteKeyDao = universityDatabase.universityRemoteKeysDao
    private val uniDatabaseDao = universityDatabase.universityDao

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UniversityEntity>,
    ): MediatorResult {
        return try {
           val currentPage =  when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            var endOfPaginationReached = false

            val result = httpClient.get<List<UniversitySerializable>>(
                route = INDONESIA_UNI,
                queryParameters = mapOf(LIMIT to 20, OFFSET to currentPage.times(20)),
            ).map { uniResponse ->
                uniResponse.map { universitySerializable ->
                    universitySerializable.toUniversityEntity()
                }
            }

            result.map { uniList ->
                endOfPaginationReached = uniList.isEmpty()

                if (loadType == LoadType.REFRESH) {
                    uniDatabaseDao.deleteAllUni()
                    remoteKeyDao.deleteAllRemoteKeys()
                }


                val prePage = if (currentPage == 0) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1

                universityDatabase.withTransaction {
                    val keys = uniList.map {
                        UniversityRemoteKeysEntity(
                            name = it.uniName,
                            prevKey = prePage,
                            nextKey = nextPage
                        )
                    }

                    remoteKeyDao.addRemoteKeys(keys)
                    uniDatabaseDao.addAllUni(uniList)
                }
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }catch(e: UnresolvedAddressException) {
            e.printStackTrace()
            MediatorResult.Error(e)
        } catch (e: SerializationException) {
            e.printStackTrace()
            MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, UniversityEntity>
    ) : UniversityRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.uniName?.let { id ->
                remoteKeyDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UniversityEntity>
    ) : UniversityRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { it ->
            remoteKeyDao.getRemoteKeys(uniName = it.uniName)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, UniversityEntity>
    ) : UniversityRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { it ->
            remoteKeyDao.getRemoteKeys(uniName = it.uniName)
        }
    }
}