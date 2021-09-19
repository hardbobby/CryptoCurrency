package com.hardbobby.cryptocurrencyapp.presentation.datafeed.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hardbobby.cryptocurrencyapp.R
import com.hardbobby.cryptocurrencyapp.databinding.FragmentDataFeedBinding
import com.hardbobby.cryptocurrencyapp.presentation.base.viewBinding
import com.hardbobby.cryptocurrencyapp.presentation.datafeed.viewmodel.DataFeedViewModel
import com.hardbobby.cryptocurrencyapp.presentation.utils.NumberHelper
import com.hardbobby.cryptocurrencyapp.presentation.utils.orZero
import com.hardbobby.data.common.Constants.DOGE_LABEL
import com.hardbobby.data.common.Constants.ETH_LABEL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataFeedFragment : Fragment(R.layout.fragment_data_feed) {
    private val binding by viewBinding(FragmentDataFeedBinding::bind)
    private val viewModel: DataFeedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.getData()
    }

    private fun setUpObserver() {
        viewModel.webSocketModelView.observe(viewLifecycleOwner) { data ->
            data.handleResult(
                successDataBlock = { result ->
                    when (result.symbol) {
                        DOGE_LABEL -> binding.textViewPriceDoge.text =
                            NumberHelper.formatPrice(result.topTierFullVolume.orZero())
                        ETH_LABEL -> binding.textViewPriceEth.text =
                            NumberHelper.formatPrice(result.topTierFullVolume.orZero())
                    }
                }
            )
        }
    }
}