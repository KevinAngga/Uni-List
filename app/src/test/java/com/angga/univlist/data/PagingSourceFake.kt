package com.angga.univlist.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.angga.univlist.domain.model.University

class PagingSourceFake(
    private val data: List<University>
) : PagingSource<Int, University>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, University> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, University>): Int? = null
}