package com.main.hilttutorial.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.main.hilttutorial.model.Blog
import com.main.hilttutorial.network.BlogNetworkEntity
import com.main.hilttutorial.network.BlogRetrofit
import com.main.hilttutorial.network.NetworkMapper
import com.main.hilttutorial.util.EntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson):Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit:Retrofit.Builder):BlogRetrofit{
            return retrofit
                .build()
                .create(BlogRetrofit::class.java)
    }
}