package com.example.pelacakkontak.ui.vaccine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaccineViewModel @Inject constructor(vaccineRepository: VaccineRepository): ViewModel() {

    sealed class VaccineEvent {
        object Loading: VaccineEvent()
        object LoadingFinished: VaccineEvent()
    }

    private val vaccineCertFlow = vaccineRepository.list()
    val vaccineCerts = vaccineCertFlow.asLiveData()

    private val vaccineEventChannel = Channel<VaccineEvent>()
    val vaccineEvents = vaccineEventChannel.receiveAsFlow()

    fun load() = viewModelScope.launch {
        vaccineEventChannel.send(VaccineEvent.Loading)
    }

    fun endLoad() = viewModelScope.launch {
        vaccineEventChannel.send(VaccineEvent.LoadingFinished)
    }
}
