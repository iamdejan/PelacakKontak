package com.example.pelacakkontak.di

import com.example.pelacakkontak.ui.vaccine.VaccineRepository
import com.example.pelacakkontak.ui.vaccine.VaccineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun provideVaccineRepository(vaccineRepository: VaccineRepositoryImpl): VaccineRepository

}
