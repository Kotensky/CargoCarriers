package com.kotensky.cargocarriers.model.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kotensky.cargocarriers.model.entities.CarrierEntity
import com.kotensky.cargocarriers.model.room.dao.CarrierDao

@Database(entities = [(CarrierEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun carrierDao(): CarrierDao
}