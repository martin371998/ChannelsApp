package com.example.myapp.di

import com.example.myapp.network.remote.ChannelsRemote
import com.example.myapp.network.remote.ChannelsRemoteImpl
import com.example.myapp.network.repository.ChannelsRepository
import com.example.myapp.network.repository.ChannelsRepositoryImpl
import com.example.myapp.network.useCase.ChannelsUseCase
import com.example.myapp.network.useCase.ChannelsUseCaseImpl
import com.example.myapp.presentation.ChannelsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModules = module {
    single { ChannelsRepositoryImpl(get()) } bind ChannelsRepository::class
    single { ChannelsRemoteImpl(get()) } bind ChannelsRemote::class
    single { ChannelsUseCaseImpl(get()) } bind ChannelsUseCase::class
    viewModel { ChannelsViewModel(get()) }
}