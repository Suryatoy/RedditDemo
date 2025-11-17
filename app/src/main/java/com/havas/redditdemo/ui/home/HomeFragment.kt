package com.havas.redditdemo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.havas.redditdemo.R
import com.havas.redditdemo.ui.detail.DetailsActivity
import com.havas.redditdemo.util.toParcelable
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A [Fragment] that displays a list of Reddit posts.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var redditAdapter: RedditAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    /**
     * Inflates the layout for this fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /**
     * Called immediately after [.onCreateView] has returned, but before any saved state has been restored in to the view.
     *
     * @param view The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setupRecyclerView()
        observeUiState()
    }

    /**
     * Initializes the views in the layout.
     *
     * @param fragmentView The view of the fragment.
     */
    private fun initViews(fragmentView: View) {
        recyclerView = fragmentView.findViewById(R.id.posts_recyclerview)
        progressBar = fragmentView.findViewById(R.id.progress_bar)
    }

    /**
     * Sets up the [RecyclerView] with its adapter and layout manager.
     */
    private fun setupRecyclerView() {
        redditAdapter = RedditAdapter { post ->
            val intent = Intent(requireActivity(), DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.EXTRA_POST, post.toParcelable())
            }
            startActivity(intent)
        }
        recyclerView.apply {
            adapter = redditAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    /**
     * Observes the UI state from the [HomeViewModel] and updates the UI accordingly.
     */
    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

                if (state.error != null) {
                    recyclerView.visibility = View.GONE
                    Snackbar.make(requireView(), state.error, Snackbar.LENGTH_LONG).show()
                } else {
                    recyclerView.visibility = View.VISIBLE
                }

                redditAdapter.submitList(state.posts)
            }
        }
    }
}
