package com.example.pelacakkontak.ui.healthtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HealthTestViewModel @Inject constructor(healthTestRepository: HealthTestRepository): ViewModel() {

    sealed class HealthTestEvent {
        object Loading: HealthTestEvent()
        object LoadingFinished: HealthTestEvent()
    }

    private val healthTestsFlow = healthTestRepository.list()
    val healthTests = healthTestsFlow.asLiveData()

    private val healthEventChannel = Channel<HealthTestEvent>()
    val healthEvents = healthEventChannel.receiveAsFlow()

    fun load() = viewModelScope.launch {
        healthEventChannel.send(HealthTestEvent.Loading)
    }

    fun endLoad() = viewModelScope.launch {
        healthEventChannel.send(HealthTestEvent.LoadingFinished)
    }
}
