package com.main.hilttutorial.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.main.hilttutorial.R
import com.main.hilttutorial.databinding.ActivityMainBinding
import com.main.hilttutorial.model.Blog
import com.main.hilttutorial.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BlogAdapter.BlogClickListeners {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var blogAdapter: BlogAdapter

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        blogAdapter = BlogAdapter(this)
        binding.blogsList.adapter = blogAdapter
        binding.blogsList.setHasFixedSize(true)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
        binding.refresh.setOnClickListener {
            showSnackBar("To be implemented")
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    displayBlogList(true)
                    displayBlogListInRecyclerView(dataState.data)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                    displayBlogList(false)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayBlogList(false)
                    displayError(dataState.exception.message)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.unknown_error))
        }
    }

    private fun showSnackBar(message: String?) {
        if (message != null) {
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun displayBlogListInRecyclerView(blogs: List<Blog>) {
        blogAdapter.submitList(blogs)
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun displayBlogList(isDisplayed: Boolean) {
        binding.blogsList.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onAddToFavouritesClicked(blog: Blog) {
        showSnackBar("'${blog.title}' added to favourites")
    }

    override fun onViewDetailsClicked(blog: Blog) {
        showSnackBar("You clicked '${blog.title}' blog")
    }

}