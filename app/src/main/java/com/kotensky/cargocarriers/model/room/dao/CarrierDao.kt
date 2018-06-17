package com.kotensky.cargocarriers.model.room.dao

import android.arch.persistence.room.*
import com.kotensky.cargocarriers.model.entities.CarrierEntity
import io.reactivex.Single

@Dao
interface CarrierDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarrier(carrierEntity: CarrierEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCarrier(carrierEntity: CarrierEntity)

    @Delete
    fun deleteCarrier(task: CarrierEntity)

    @Query("SELECT * FROM carrier")
    fun getAllCarrier(): Single<List<CarrierEntity>>

}