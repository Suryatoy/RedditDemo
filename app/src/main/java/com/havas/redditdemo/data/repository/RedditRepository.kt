package com.havas.redditdemo.data.repository

import com.havas.redditdemo.data.model.RedditPost
import com.havas.redditdemo.data.remote.RedditApi
import com.havas.redditdemo.util.Resource

/**
 * Repository for fetching Reddit data from the network.
 *
 * @param api The Reddit API service.
 */
class RedditRepository(private val api: RedditApi) {

    /**
     * Fetches the home posts from the Reddit API.
     *
     * @return A [Resource] containing a list of [RedditPost] objects.
     */
    suspend fun getHomePosts(): Resource<List<RedditPost>> {
        return try {
            val response = api.getHomePosts()
            if (response.isSuccessful) {
                val posts = response.body()?.data?.children?.map { it.data }
                Resource.Success(posts ?: emptyList())
            } else {
                Resource.Error("An error occurred: ${response.message()}")
            }
        } catch (_: Exception) {
            Resource.Error("Couldn't reach the server. Check your internet connection.")
        }
    }
}
