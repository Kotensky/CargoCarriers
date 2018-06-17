package com.kotensky.cargocarriers.model.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.kotensky.cargocarriers.util.ArrayListConverter
import com.kotensky.cargocarriers.util.ColorTypeConverter
import com.kotensky.cargocarriers.util.DateTypeConverter
import java.io.Serializable
import java.util.*

@Entity(tableName = "carrier")
open class CarrierEntity : Serializable {

    enum class ColorType {
        GREEN, YELLOW, RED
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "company_name")
    var companyName: String? = null

    var description: String? = null

    @ColumnInfo(name = "base_place")
//   todo var basePlace: PlaceEntity? = null
    var basePlace: String? = null

    @ColumnInfo(name = "contact_person")
    var contactPerson: String? = null

    @TypeConverters(ArrayListConverter::class)
    var phones: ArrayList<String>? = null

    //   todo  var directions: ArrayList<PlaceEntity>? = null
    @TypeConverters(ArrayListConverter::class)
    var directions: ArrayList<String>? = null

    @ColumnInfo(name = "last_call_date")
    @TypeConverters(DateTypeConverter::class)
    var lastCallDate: Date? = null

    @ColumnInfo(name = "color_type")
    @TypeConverters(ColorTypeConverter::class)
    var colorType: ColorType = ColorType.GREEN

    @ColumnInfo(name = "is_vip")
    var isVip: Boolean = false

}