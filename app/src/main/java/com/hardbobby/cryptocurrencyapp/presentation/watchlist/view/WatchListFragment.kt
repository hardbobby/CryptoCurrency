package com.hardbobby.cryptocurrencyapp.presentation.watchlist.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.hardbobby.cryptocurrencyapp.R
import com.hardbobby.cryptocurrencyapp.databinding.FragmentWatchListBinding
import com.hardbobby.cryptocurrencyapp.presentation.base.viewBinding
import com.hardbobby.cryptocurrencyapp.presentation.utils.*
import com.hardbobby.cryptocurrencyapp.presentation.watchlist.adapter.WatchListAdapter
import com.hardbobby.cryptocurrencyapp.presentation.watchlist.viewmodel.WatchListViewModel
import com.hardbobby.domain.common.Result
import com.hardbobby.domain.common.SimpleResult
import com.hardbobby.domain.dto.CryptoModelView
import dagger.hilt.android.AndroidEntryPoint
import observeEvent

@AndroidEntryPoint
class WatchListFragment : Fragment(R.layout.fragment_watch_list) {
    private val binding by viewBinding(FragmentWatchListBinding::bind)
    private val viewModel: WatchListViewModel by viewModels()
    private var adapter: WatchListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
        setupListener()
        viewModel.getData()
    }

    private fun setupView() {
        with(binding) {
            recyclerViewCryptoData.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = WatchListAdapter {
                viewModel.fetchNextPage()
            }
            recyclerViewCryptoData.adapter = adapter
        }
    }

    private fun setupListener() {
        with(binding) {
            pullToRefresh.setOnRefreshListener {
                textViewErrorMessage.setGone()
                recyclerViewCryptoData.setGone()
                startShimmer()
                adapter?.submitList(null)
                viewModel.refreshData()
            }
        }
    }

    private fun setupObserver() {
        viewModel.cryptoData.observe(viewLifecycleOwner, ::handleCryptoResult)
        viewModel.loading().observeEvent(viewLifecycleOwner, ::handlePageLoading)
    }

    private fun handleCryptoResult(result: SimpleResult<List<CryptoModelView>>) {
        result.handleResult(
            successDataBlock = { data ->
                onSuccessResult(data)
            }, successNoDataBlock = {
                stopShimmer()
                onFinishLoadData()
            }, failureBlock = {
                stopShimmer()
                showErrorSnackbar(it.errorMessage)
                onFinishLoadData()
            }
        ) { state ->
            when (state) {
                Result.State.Loading -> {
                    onLoadingData()
                }
            }
        }
    }

    private fun onSuccessResult(data: List<CryptoModelView>) {
        stopShimmer()
        viewModel.addListData(data)
        adapter?.submitList(viewModel.cryptoList.distinctBy {
            listOf(it.id, it.name)
        })
        onFinishLoadData()
        binding.recyclerViewCryptoData.setVisible()
    }

    private fun handlePageLoading(show: Boolean) {
        binding.linearLayoutProgressBottom.showSlidingIf(show)
    }

    private fun onLoadingData() {
        startShimmer()
        with(binding) {
            textViewErrorMessage.setGone()
            recyclerViewCryptoData.setGone()
        }
    }

    private fun stopShimmer() {
        with(binding.viewLoadingShimmer.shimmerLoading) {
            stopShimmer()
            setGone()
        }
    }

    private fun startShimmer() {
        with(binding.viewLoadingShimmer.shimmerLoading) {
            setVisible()
            startShimmer()
        }
    }

    private fun showErrorSnackbar(message: String) {
        (activity?.findViewById(android.R.id.content) as? View)?.let {
            view?.showErrorSnackbar(message)

        }
    }

    private fun onFinishLoadData() {
        with(binding) {
            handlePageLoading(false)
            pullToRefresh.isRefreshing = false
            textViewErrorMessage.showIf { viewModel.cryptoList.isNullOrEmpty() }
        }
    }
}