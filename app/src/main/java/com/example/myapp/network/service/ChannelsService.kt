package com.example.myapp.network.service

import com.example.myapp.entities.Channel
import retrofit2.http.GET

interface ChannelsService {
    @GET("/channels.json")
    suspend fun getChannels(): List<Channel>

}