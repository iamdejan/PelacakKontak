package com.example.pelacakkontak.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    sealed class LoginEvent {
        object GoToRegisterScreen : LoginEvent()
        object LoginSuccess : LoginEvent()
        data class LoginFailed(val message: String) : LoginEvent()
    }

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEvents = loginEventChannel.receiveAsFlow()

    fun onRegisterTextClicked() = viewModelScope.launch {
        loginEventChannel.send(LoginEvent.GoToRegisterScreen)
    }

    fun onLoginButtonClicked(email: String, password: String) = viewModelScope.launch {
        val response = loginRepository.login(email, password)
        if (!response.isSuccessful) {
            loginEventChannel.send(LoginEvent.LoginFailed("Something is wrong with login request"))
            return@launch
        }

        when (response.code()) {
            200 -> {
                val token = response.body()?.jwtToken?.token
                if(token == null) {
                    loginEventChannel.send(LoginEvent.LoginFailed("Token is null in response body"))
                    return@launch
                }
                // TODO dejan: save token in local storage
                loginEventChannel.send(LoginEvent.LoginSuccess)
            }
            401 -> loginEventChannel.send(LoginEvent.LoginFailed("Unauthorized"))
            500 -> loginEventChannel.send(LoginEvent.LoginFailed("Internal server error"))
        }

    }
}
