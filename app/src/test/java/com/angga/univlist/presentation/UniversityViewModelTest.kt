package com.angga.univlist.presentation

import com.angga.univlist.MainCoroutineExtension
import com.angga.univlist.data.UniversityRepositoryImplFake
import com.angga.univlist.domain.repository.UniversityRepository
import com.angga.univlist.ui.presentation.UniversityAction
import com.angga.univlist.ui.presentation.UniversityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@OptIn(ExperimentalCoroutinesApi::class)
class UniversityViewModelTest {
    private lateinit var universityRepository: UniversityRepository
    private lateinit var viewModel: UniversityViewModel

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineExtension = MainCoroutineExtension()
    }


    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainCoroutineExtension.testDispatcher)
        universityRepository = UniversityRepositoryImplFake()
        viewModel = UniversityViewModel(universityRepository)
    }

    @Test
    fun `verify onTextChange via onAction`() = runTest {
        val input = "University Test"
        viewModel.onAction(UniversityAction.OnTextChange(input))
        Assertions.assertEquals(input, viewModel.state.searchValue)
    }

}