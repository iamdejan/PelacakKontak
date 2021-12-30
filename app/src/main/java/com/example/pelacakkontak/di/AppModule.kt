package com.example.pelacakkontak.di

import com.example.pelacakkontak.ui.healthtest.HealthTestRepository
import com.example.pelacakkontak.ui.healthtest.HealthTestRepositoryImpl
import com.example.pelacakkontak.ui.vaccine.VaccineRepository
import com.example.pelacakkontak.ui.vaccine.VaccineRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://peduli.xyz:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Singleton
    @Binds
    abstract fun provideVaccineRepository(vaccineRepository: VaccineRepositoryImpl): VaccineRepository

    @Singleton
    @Binds
    abstract fun provideHealthTestRepository(healthTestRepository: HealthTestRepositoryImpl): HealthTestRepository

}
