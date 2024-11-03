package com.example.myapp.network.useCase

import com.example.myapp.entities.Channel
import com.example.myapp.network.repository.ChannelsRepository
import kotlinx.coroutines.flow.Flow

class ChannelsUseCaseImpl(
    private val channelsRepository: ChannelsRepository
) : ChannelsUseCase {
    override suspend fun getChannels(): Flow<List<Channel>> {
        return channelsRepository.getChannels()
    }
}