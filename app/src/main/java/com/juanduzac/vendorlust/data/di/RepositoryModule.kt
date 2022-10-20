package com.juanduzac.vendorlust.data.di

import com.juanduzac.vendorlust.data.repository.VendorsRepositoryImpl
import com.juanduzac.vendorlust.domain.repository.VendorsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVendorsRepository(
        vendorsRepositoryImpl: VendorsRepositoryImpl
    ): VendorsRepository
}
