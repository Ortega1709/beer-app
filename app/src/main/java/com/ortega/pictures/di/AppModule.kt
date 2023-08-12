package com.ortega.pictures.di

import android.content.Context
import androidx.room.Room
import com.ortega.pictures.data.datasource.local.PhotosDB
import com.ortega.pictures.data.datasource.remote.PhotosAPI
import com.ortega.pictures.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePhotosAPI(retrofit: Retrofit): PhotosAPI {
        return retrofit.create(PhotosAPI::class.java)
    }

    @Singleton
    @Provides
    fun providePhotosDB(@ApplicationContext context: Context): PhotosDB {
        return Room.databaseBuilder(
            context = context,
            klass = PhotosDB::class.java,
            name = "note.db"
        ).build()
    }

}