package com.hardbobby.cryptocurrencyapp.presentation.login.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.form
import com.hardbobby.cryptocurrencyapp.R
import com.hardbobby.cryptocurrencyapp.databinding.FragmentLoginBinding
import com.hardbobby.cryptocurrencyapp.presentation.base.viewBinding
import com.hardbobby.cryptocurrencyapp.presentation.login.viewmodel.LoginViewModel
import com.hardbobby.cryptocurrencyapp.presentation.main.view.MainActivity
import com.hardbobby.cryptocurrencyapp.presentation.utils.LoginFlow
import com.hardbobby.cryptocurrencyapp.presentation.utils.ValidationHelper.assertEmail
import com.hardbobby.cryptocurrencyapp.presentation.utils.ValidationHelper.assertNotEmpty
import com.hardbobby.cryptocurrencyapp.presentation.utils.showSuccessSnackbar
import com.hardbobby.cryptocurrencyapp.presentation.utils.showToastMessage
import dagger.hilt.android.AndroidEntryPoint
import observeEvent

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        setupViewListener()
    }

    private fun setUpObserver() {
        viewModel.loginFlow.observe(viewLifecycleOwner) {
            if (it == LoginFlow.MainHomePage) {
                navigateToWatchList()
            }
        }
        viewModel.snackbarMessage().observeEvent(viewLifecycleOwner,
            {
                binding.root.showSuccessSnackbar(it)
            }
        )
    }

    private fun setupViewListener() {
        with(binding) {
            form {
                inputLayout(textInputLayoutEmail) {
                    assertNotEmpty()
                    assertEmail()
                }
                inputLayout(textInputLayoutPassword) {
                    assertNotEmpty()
                }
                submitWith(R.id.button_login) {
                    if (it.success()) {
                        viewModel.onUserLogin(editTextEmail.text.toString())
                    }
                }
            }
        }
    }

    private fun navigateToWatchList() {
        val action = LoginFragmentDirections.actionLoginToWatchList()
        if (findNavController().currentDestination?.id == R.id.loginFragment) {
            findNavController().navigate(action)
        }
    }

    override fun onAttach(context: Context) {
        (activity as? MainActivity?)?.hideBottomNavigation()
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        (activity as? MainActivity?)?.showBottomNavigation()
    }

}