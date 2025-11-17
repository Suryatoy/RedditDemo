package com.havas.redditdemo.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.havas.redditdemo.ui.details.DetailsScreen
import com.havas.redditdemo.util.RedditPostParcelable
import dagger.hilt.android.AndroidEntryPoint

/**
 * An [ComponentActivity] that displays the details of a single Reddit post using Jetpack Compose.
 * This activity is an entry point for Hilt.
 */
@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    /**
     * Companion object containing constants for the activity.
     */
    companion object {
        /**
         * The key for the [RedditPostParcelable] extra passed to this activity.
         */
        const val EXTRA_POST = "EXTRA_POST"
    }

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc. This method also provides a
     * [Bundle] containing the activity's previously frozen state, if there was one.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     * shut down then this [Bundle] contains the data it most recently supplied in
     * [onSaveInstanceState]. **Note: Otherwise it is null.**
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val post = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_POST, RedditPostParcelable::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_POST)
        }

        if (post != null) {
            setContent {
                DetailsScreen(post = post) {
                    finish()
                }
            }
        }
    }
}
