package com.example.pelacakkontak.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pelacakkontak.util.TEST_LIST
import com.example.pelacakkontak.util.VACCINE_CERT_LIST
import com.example.pelacakkontak.util.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    sealed class HomeEvent {
        object GoToVaccineFragment : HomeEvent()
        object GoToTestFragment : HomeEvent()
    }

    private val homeEventChannel = Channel<HomeEvent>()
    val homeEvents = homeEventChannel.receiveAsFlow()

    fun onMenuClicked(menu: Menu) = viewModelScope.launch {
        when (menu.destScren) {
            VACCINE_CERT_LIST -> {
                homeEventChannel.send(HomeEvent.GoToVaccineFragment)
            }
            TEST_LIST -> {
                homeEventChannel.send(HomeEvent.GoToTestFragment)
            }
            else -> {
            }
        }.exhaustive
    }
}
