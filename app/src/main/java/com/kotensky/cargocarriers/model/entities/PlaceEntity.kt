package com.kotensky.cargocarriers.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

class PlaceEntity : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo(name = "name")
    var name: String? = null
}