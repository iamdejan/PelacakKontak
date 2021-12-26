package com.example.pelacakkontak.ui.vaccine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaccineViewModel @Inject constructor(private val vaccineRepository: VaccineRepository): ViewModel() {

    sealed class VaccineEvent {
        object Loading: VaccineEvent()
        object LoadingFinished: VaccineEvent()
    }

    private val vaccineCertsFlow = vaccineRepository.list()
    val vaccineCerts = vaccineCertsFlow.asLiveData()

    private val vaccineEventChannel = Channel<VaccineEvent>()
    val vaccineEvents = vaccineEventChannel.receiveAsFlow()

    fun load() = viewModelScope.launch {
        vaccineEventChannel.send(VaccineEvent.Loading)
    }

    fun onLoadingFinished() = viewModelScope.launch {
        vaccineEventChannel.send(VaccineEvent.LoadingFinished)
    }
}
