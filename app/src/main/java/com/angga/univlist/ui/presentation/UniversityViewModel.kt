package com.angga.univlist.ui.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.angga.univlist.ui.domain.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val universityRepository: UniversityRepository
) : ViewModel() {
    var state by mutableStateOf(UniversityState())
        private set

    init {
        viewModelScope.launch {
            getUniversity()
        }
    }

    fun onAction(action: UniversityAction) {
        when(action) {
            is UniversityAction.OnTextChange -> {
                state = state.copy(
                    searchValue = action.text,
                )
                filterUniversities(action.text.trim())
            }
            else -> {}
        }
    }

    private fun filterUniversities(universityName: String) {
        if (universityName.isNotEmpty()) {
            val result = universityRepository
                .getUniversitiesByName(universityName)
                .cachedIn(viewModelScope)
            state = state.copy(
                universityList = result,
            )
        } else {
            getUniversity()
        }
    }

    private fun getUniversity() {
        val result = universityRepository
            .getUniversitiesList()
            .cachedIn(viewModelScope)
        state = state.copy(
            universityList = result,
        )
    }
}