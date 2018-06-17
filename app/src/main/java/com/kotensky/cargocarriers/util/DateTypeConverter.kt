package com.kotensky.cargocarriers.util

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateTypeConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun toLong(date: Date?): Long? = date?.time

}