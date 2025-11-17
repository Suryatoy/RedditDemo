package com.havas.redditdemo.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the top-level response from the Reddit API.
 *
 * @param kind The kind of the response.
 * @param data The data of the response.
 */
@JsonClass(generateAdapter = true)
data class RedditApiResponse(
    val kind: String,
    val data: ListingData
)

/**
 * Represents a listing of Reddit posts.
 *
 * @param after The token for the next page of results.
 * @param before The token for the previous page of results.
 * @param children The list of children in the listing.
 */
@JsonClass(generateAdapter = true)
data class ListingData(
    val after: String?,
    val before: String?,
    val children: List<RedditChild>
)

/**
 * Represents a child in a Reddit listing.
 *
 * @param kind The kind of the child.
 * @param data The data of the child.
 */
@JsonClass(generateAdapter = true)
data class RedditChild(
    val kind: String,
    val data: RedditPost
)

/**
 * Represents a single Reddit post.
 *
 * @param id The ID of the post.
 * @param title The title of the post.
 * @param selfText The self-text of the post, if any.
 * @param ups The number of upvotes.
 * @param numComments The number of comments.
 * @param thumbnail The URL of the thumbnail image.
 * @param preview The preview data for the post.
 */
@JsonClass(generateAdapter = true)
data class RedditPost(
    val id: String,
    val title: String,
    @Json(name = "selftext") val selfText: String?,
    val ups: Int,
    @Json(name = "num_comments") val numComments: Int,
    val thumbnail: String?,
    val preview: Preview?
)

/**
 * Represents the preview data for a Reddit post.
 *
 * @param images The list of images in the preview.
 */
@JsonClass(generateAdapter = true)
data class Preview(
    val images: List<PreviewImage>?
)

/**
 * Represents a preview image for a Reddit post.
 *
 * @param source The source of the preview image.
 */
@JsonClass(generateAdapter = true)
data class PreviewImage(
    val source: PreviewSource
)

/**
 * Represents the source of a preview image.
 *
 * @param url The URL of the image.
 * @param width The width of the image.
 * @param height The height of the image.
 */
@JsonClass(generateAdapter = true)
data class PreviewSource(
    val url: String,
    val width: Int,
    val height: Int
)
