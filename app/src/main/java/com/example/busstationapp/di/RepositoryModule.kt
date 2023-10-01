package com.example.busstationapp.di

import com.example.busstationapp.data.domain.BusStationRepository
import com.example.busstationapp.data.domain.BusStationRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindBusStationRepository(busStationRepositoryImp: BusStationRepositoryImp): BusStationRepository
}