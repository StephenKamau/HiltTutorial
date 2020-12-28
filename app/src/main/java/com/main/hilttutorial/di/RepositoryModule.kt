package com.main.hilttutorial.di

import com.main.hilttutorial.network.BlogRetrofit
import com.main.hilttutorial.network.NetworkMapper
import com.main.hilttutorial.repository.MainRepository
import com.main.hilttutorial.room.BlogDao
import com.main.hilttutorial.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(
            blogDao,
            blogRetrofit,
            cacheMapper,
            networkMapper
        )
    }
}