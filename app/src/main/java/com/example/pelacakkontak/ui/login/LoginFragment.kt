package com.example.pelacakkontak.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentLoginBinding
import com.example.pelacakkontak.ui.BaseFragment
import com.example.pelacakkontak.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)
        binding.apply {
            textViewLoginRegister.setOnClickListener {
                viewModel.onRegisterTextClicked()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginEvent.collect { event ->
                when (event) {
                    is LoginViewModel.LoginEvent.GoToRegisterScreen -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }
}
