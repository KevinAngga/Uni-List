package com.angga.univlist.ui.presentation

import androidx.paging.PagingData
import com.angga.univlist.domain.model.University
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class UniversityState(
    val universityList : Flow<PagingData<University>> = flow { PagingData.empty<University>() },
    val searchValue : String = "",
)
