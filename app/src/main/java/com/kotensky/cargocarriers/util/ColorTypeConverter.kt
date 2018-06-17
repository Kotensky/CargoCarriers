package com.kotensky.cargocarriers.util

import android.arch.persistence.room.TypeConverter
import com.kotensky.cargocarriers.model.entities.CarrierEntity

class ColorTypeConverter {

    @TypeConverter
    fun toColorType(ordinal: Int): CarrierEntity.ColorType {
        return CarrierEntity.ColorType.values()[ordinal]
    }

    @TypeConverter
    fun toOrdinal(colorType: CarrierEntity.ColorType): Int? {
        return colorType.ordinal
    }

}