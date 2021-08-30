package com.example.pelacakkontak.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pelacakkontak.R
import com.example.pelacakkontak.databinding.FragmentRegisterBinding
import com.example.pelacakkontak.ui.BaseFragment
import com.example.pelacakkontak.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRegisterBinding.bind(view)
        binding.apply {
            textViewRegisterLogin.setOnClickListener {
                viewModel.onLoginTextClicked()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.registerEvents.collect { event ->
                when (event) {
                    is RegisterViewModel.RegisterEvent.GoToLoginScreen -> {
                        val action =
                            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }
}
