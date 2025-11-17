package com.havas.redditdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main and single activity of the application, built on a single-activity architecture.
 *
 * This activity serves as the main container for the app's UI and hosts the
 * NavHostFragment which manages the navigation between different screens (fragments).
 * It is annotated with [@AndroidEntryPoint] to enable dependency injection with Hilt.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
