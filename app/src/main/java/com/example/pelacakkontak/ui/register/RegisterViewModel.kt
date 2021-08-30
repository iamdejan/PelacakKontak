package com.example.pelacakkontak.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    sealed class RegisterEvent {
        object GoToLoginScreen : RegisterEvent()
    }

    private val registerEventChannel = Channel<RegisterEvent>()
    val registerEvents = registerEventChannel.receiveAsFlow()

    fun onLoginTextClicked() = viewModelScope.launch {
        registerEventChannel.send(RegisterEvent.GoToLoginScreen)
    }
}
