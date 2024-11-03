package com.example.myapp.network.repository

import com.example.myapp.entities.Channel
import kotlinx.coroutines.flow.Flow

interface ChannelsRepository {
    suspend fun getChannels(): Flow<List<Channel>>
}