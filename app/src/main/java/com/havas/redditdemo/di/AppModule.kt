package com.havas.redditdemo.di

import com.havas.redditdemo.data.remote.RedditApi
import com.havas.redditdemo.data.repository.RedditRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides application-level dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of [RedditRepository].
     *
     * @param api The [RedditApi] instance.
     * @return A singleton instance of [RedditRepository].
     */
    @Provides
    @Singleton
    fun provideRedditRepository(api: RedditApi): RedditRepository {
        return RedditRepository(api)
    }
}
