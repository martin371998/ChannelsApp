package com.example.myapp.network.remote

import com.example.myapp.entities.Channel

interface ChannelsRemote {
    suspend fun getChannels(): List<Channel>
}