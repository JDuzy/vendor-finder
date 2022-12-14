package com.juanduzac.vendorlust.data.di

import android.app.Application
import androidx.room.Room
import com.juanduzac.vendorlust.data.local.VendorDatabase
import com.juanduzac.vendorlust.data.remote.api.VendorsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVendorsApi(): VendorsApi {
        // val logging = HttpLoggingInterceptor()
        // set your desired log level
        // logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        // add your other interceptors …
        // add logging as last interceptor
        // httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesVendorDatabase(app: Application): VendorDatabase {
        return Room.databaseBuilder(
            app,
            VendorDatabase::class.java,
            "vendorsdb"
        ).build()
    }
}
