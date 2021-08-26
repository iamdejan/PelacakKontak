package com.example.pelacakkontak.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    sealed class LoginEvent {
        object GoToRegisterScreen : LoginEvent()
    }

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEvent = loginEventChannel.receiveAsFlow()

    fun onRegisterTextClicked() = viewModelScope.launch {
        loginEventChannel.send(LoginEvent.GoToRegisterScreen)
    }
}
