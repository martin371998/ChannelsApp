package com.example.myapp.network.repository

import com.example.myapp.entities.Channel
import com.example.myapp.network.remote.ChannelsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChannelsRepositoryImpl(private val channelsRemote: ChannelsRemote) : ChannelsRepository {
    override suspend fun getChannels(): Flow<List<Channel>> =
        flow { emit(channelsRemote.getChannels()) }
}