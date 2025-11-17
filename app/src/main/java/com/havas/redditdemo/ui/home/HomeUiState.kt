package com.havas.redditdemo.ui.home

import com.havas.redditdemo.data.model.RedditPost

/**
 * Represents the state of the Home screen.
 *
 * @param posts The list of Reddit posts to display.
 * @param isLoading True if the posts are currently being loaded, false otherwise.
 * @param error A message describing an error if one occurred, null otherwise.
 */
data class HomeUiState(
    val posts: List<RedditPost> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
