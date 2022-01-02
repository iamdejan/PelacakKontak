package com.example.pelacakkontak.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pelacakkontak.datastore.TokenDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenDataStoreRepository: TokenDataStoreRepository
) : ViewModel() {

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
        if (email.isBlank()) {
            loginEventChannel.send(LoginEvent.LoginFailed("Mohon masukkan email"))
            return@launch
        }
        if (password.isBlank()) {
            loginEventChannel.send(LoginEvent.LoginFailed("Mohon masukkan password"))
            return@launch
        }
        val response = loginRepository.login(email, password)
        when (response.code()) {
            200 -> {
                val token = response.body()?.jwtToken?.token
                if (token == null) {
                    loginEventChannel.send(LoginEvent.LoginFailed("Token kosong dari server"))
                    return@launch
                }
                tokenDataStoreRepository.setBearerToken(token)
                loginEventChannel.send(LoginEvent.LoginSuccess)
            }
            401 -> loginEventChannel.send(LoginEvent.LoginFailed("Email dan/atau password salah"))
            404 -> loginEventChannel.send(LoginEvent.LoginFailed("URL tidak ditemukan"))
            in 500..599 -> loginEventChannel.send(LoginEvent.LoginFailed("Error pada server kami"))
        }
    }
}
