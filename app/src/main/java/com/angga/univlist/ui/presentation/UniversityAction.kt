package com.angga.univlist.ui.presentation

sealed interface UniversityAction {
    data class OnTextChange(val text : String) : UniversityAction
}