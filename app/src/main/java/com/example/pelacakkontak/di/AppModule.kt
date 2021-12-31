package com.example.pelacakkontak.di

import com.example.pelacakkontak.api.AuthApi
import com.example.pelacakkontak.datastore.TokenDataStoreRepository
import com.example.pelacakkontak.datastore.TokenDataStoreRepositoryImpl
import com.example.pelacakkontak.ui.healthtest.HealthTestRepository
import com.example.pelacakkontak.ui.healthtest.HealthTestRepositoryImpl
import com.example.pelacakkontak.ui.login.LoginRepository
import com.example.pelacakkontak.ui.login.LoginRepositoryImpl
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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(AuthApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Binds
    @Singleton
    abstract fun provideVaccineRepository(vaccineRepository: VaccineRepositoryImpl): VaccineRepository

    @Binds
    @Singleton
    abstract fun provideHealthTestRepository(healthTestRepository: HealthTestRepositoryImpl): HealthTestRepository

    @Binds
    @Singleton
    abstract fun provideLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun provideTokenDataStoreRepository(tokenDataStoreRepository: TokenDataStoreRepositoryImpl): TokenDataStoreRepository
}
