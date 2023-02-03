package com.albertson.codetest.acronymfinder.ui.main

import android.content.res.Resources
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertson.codetest.acronymfinder.AcronymFinderApp
import com.albertson.codetest.acronymfinder.R
import com.albertson.codetest.acronymfinder.databinding.FragmentMainBinding
import com.albertson.codetest.acronymfinder.network.ApiResource
import com.albertson.codetest.acronymfinder.network.Status
import com.albertson.codetest.acronymfinder.network.model.Lf
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as AcronymFinderApp).getApplicationComponent()
            .fragmentComponent()
            .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchViewListener()
    }

    private fun setupRecyclerView() {
        with(binding.resultsList) {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
            adapter = MainAdapter()
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    (this.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }

    private fun setupSearchViewListener() {
        with(binding.searchView) {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.performSearch(query).observe(viewLifecycleOwner) {
                            when (it.status) {
                                Status.LOADING -> {
                                    updateLoadingSpinner(true)
                                    updateErrorMessageVisibility(false)
                                }
                                Status.SUCCESS -> {
                                    updateLoadingSpinner(false)
                                    updateErrorMessageVisibility(false)
                                    if (it.data.isNullOrEmpty()) {
                                        //show empty message.
                                        updateErrorMessage(getString(R.string.empty_results_message, query))
                                    } else {
                                        updateResultsList(it.data)
                                    }
                                }
                                Status.ERROR -> {
                                    updateLoadingSpinner(false)
                                    it.message?.let { msg -> updateErrorMessage(msg) }
                                }
                            }
                        }
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.i(this@MainFragment::class.simpleName, "query $newText")
                    if (newText?.length == 0) {
                        // clear list once query gets cleared.
                        updateResultsList(listOf())
                    }
                    return false
                }
            })
        }
    }

    private fun updateLoadingSpinner(show: Boolean) {
        with(binding) {
            resultsList.isVisible = !show
            progressBar.isVisible = show
        }
    }

    private fun updateResultsList(results: List<Lf>) {
        with(binding.resultsList) {
            isVisible = true
            (adapter as MainAdapter).results = results
        }
    }

    private fun updateErrorMessage(message: String) {
        with(binding.errorMessage) {
            text = message
        }
        updateErrorMessageVisibility(true)
    }

    private fun updateErrorMessageVisibility(show: Boolean) {
        with(binding) {
            errorMessage.isVisible = show
            resultsList.isVisible = !show
        }
    }
}