package com.ortega.pictures.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.ortega.pictures.data.datasource.local.BeerDB
import com.ortega.pictures.data.datasource.remote.BeerRemoteMediator
import com.ortega.pictures.data.datasource.remote.PunkAPI
import com.ortega.pictures.data.repository.BeerRepository
import com.ortega.pictures.domain.entity.BeerEntity
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
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePunkAPI(retrofit: Retrofit): PunkAPI {
        return retrofit.create(PunkAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideBeerDB(@ApplicationContext context: Context): BeerDB {
        return Room.databaseBuilder(
            context = context,
            klass = BeerDB::class.java,
            name = "beer.db"
        ).build()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Singleton
    @Provides
    fun provideBeerPager(beerDB: BeerDB, beerRepository: BeerRepository): Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 7),
            remoteMediator = BeerRemoteMediator(beerDB = beerDB, beerRepository = beerRepository),
            pagingSourceFactory = { beerDB.beersDAO().pagingSource() }
        )
    }

}