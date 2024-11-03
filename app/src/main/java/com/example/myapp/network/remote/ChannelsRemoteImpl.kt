package com.example.myapp.network.remote

import com.example.myapp.entities.Channel
import com.example.myapp.network.service.ChannelsService

class ChannelsRemoteImpl(private val channelsService: ChannelsService) : ChannelsRemote {
    override suspend fun getChannels(): List<Channel> =
        channelsService.getChannels()
}