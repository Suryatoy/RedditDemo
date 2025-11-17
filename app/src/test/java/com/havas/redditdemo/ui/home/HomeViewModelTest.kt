package com.havas.redditdemo.ui.home

import app.cash.turbine.test
import com.havas.redditdemo.MainCoroutineRule
import com.havas.redditdemo.data.model.RedditPost
import com.havas.redditdemo.data.repository.RedditRepository
import com.havas.redditdemo.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val repository: RedditRepository = mockk()

    @Test
    fun `fetchHomePosts success`() = runTest {
        // Given
        val posts = listOf(RedditPost("1", "Title", "", 10, 5, "", null))
        coEvery { repository.getHomePosts() } returns Resource.Success(posts)

        // When
        val viewModel = HomeViewModel(repository)

        // Then
        viewModel.uiState.test {
            assertEquals(HomeUiState(isLoading = true), awaitItem())
            assertEquals(HomeUiState(posts = posts), awaitItem())
        }
    }

    @Test
    fun `fetchHomePosts error`() = runTest {
        // Given
        val errorMessage = "An error occurred"
        coEvery { repository.getHomePosts() } returns Resource.Error(errorMessage)

        // When
        val viewModel = HomeViewModel(repository)

        // Then
        viewModel.uiState.test {
            assertEquals(HomeUiState(isLoading = true), awaitItem())
            assertEquals(HomeUiState(error = errorMessage), awaitItem())
        }
    }
}
