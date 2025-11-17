package com.havas.redditdemo.util

import android.os.Parcelable
import com.havas.redditdemo.data.model.RedditPost
import kotlinx.parcelize.Parcelize

/**
 * A parcelable representation of a Reddit post, used for passing data between screens.
 */
@Parcelize
data class RedditPostParcelable(
    val title: String,
    val selfText: String?,
    val ups: Int,
    val numComments: Int,
    val imageUrl: String?
) : Parcelable

/**
 * Converts a [RedditPost] to a [RedditPostParcelable].
 *
 * @return A [RedditPostParcelable] representation of the [RedditPost].
 */
fun RedditPost.toParcelable(): RedditPostParcelable {
    return RedditPostParcelable(
        title = title,
        selfText = selfText,
        ups = ups,
        numComments = numComments,
        imageUrl = preview?.images?.firstOrNull()?.source?.url?.replace("&amp;", "&")
    )
}
