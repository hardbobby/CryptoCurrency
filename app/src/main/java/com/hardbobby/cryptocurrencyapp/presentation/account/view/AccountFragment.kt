package com.hardbobby.cryptocurrencyapp.presentation.account.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.hardbobby.cryptocurrencyapp.R
import com.hardbobby.cryptocurrencyapp.databinding.FragmentAccountBinding
import com.hardbobby.cryptocurrencyapp.presentation.base.viewBinding
import com.hardbobby.cryptocurrencyapp.presentation.main.view.MainActivity
import com.hardbobby.cryptocurrencyapp.presentation.utils.setOnClickWithThrottle

class AccountFragment : Fragment(R.layout.fragment_account) {
    private val binding by viewBinding(FragmentAccountBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding.constraintLayoutLogout.setOnClickWithThrottle {
            (activity as? MainActivity?)?.logout()
        }
    }
}