package com.example.myapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.entities.Channel
import com.example.myapp.network.useCase.ChannelsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChannelsViewModel(private val channelsUseCase: ChannelsUseCase) : ViewModel() {

    init {
        getChannels()
    }

    private val channels = MutableStateFlow<List<Channel>?>(null)
    val channelList = channels.asStateFlow()

    fun getChannels() {
        viewModelScope.launch {
            channelsUseCase.getChannels().collect { list ->
                if (list.isNotEmpty()) channels.value = list
            }
        }
    }

}