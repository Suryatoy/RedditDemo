package com.havas.redditdemo.data.remote

import com.havas.redditdemo.data.model.RedditApiResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit interface for the Reddit API.
 */
interface RedditApi {
    /**
     * Fetches the home posts from the Reddit API.
     *
     * @return A [Response] containing a [RedditApiResponse].
     */
    @GET("/.json")
    suspend fun getHomePosts(): Response<RedditApiResponse>
}
