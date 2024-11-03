package com.example.myapp.network.useCase

import com.example.myapp.entities.Channel
import kotlinx.coroutines.flow.Flow

interface ChannelsUseCase {
    suspend fun getChannels(): Flow<List<Channel>>
}