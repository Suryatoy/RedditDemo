package com.havas.redditdemo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.havas.redditdemo.data.repository.RedditRepository
import com.havas.redditdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for the home screen, responsible for fetching and managing Reddit posts.
 *
 * @param repository The repository for fetching Reddit data.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RedditRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    /**
     * The UI state for the home screen, exposed as a [StateFlow].
     */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchHomePosts()
    }

    /**
     * Fetches the home posts from the repository and updates the UI state.
     */
    private fun fetchHomePosts() {
        _uiState.value = HomeUiState(isLoading = true)
        viewModelScope.launch {
            when (val resource = repository.getHomePosts()) {
                is Resource.Success -> {
                    _uiState.value = HomeUiState(posts = resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    _uiState.value = HomeUiState(error = resource.message)
                }
            }
        }
    }
}
