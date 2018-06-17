package com.workhardapp.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.kotensky.cargocarriers.application.CarrierApplication
import com.kotensky.cargocarriers.model.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule constructor(private val application: CarrierApplication) {

    @Provides
    @Singleton
    fun provideApplication(): CarrierApplication = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "carrier-db").build()

    @Provides fun providesCarrierDao(database: AppDatabase) = database.carrierDao()
}