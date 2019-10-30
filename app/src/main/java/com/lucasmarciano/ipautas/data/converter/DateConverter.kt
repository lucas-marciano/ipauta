package com.lucasmarciano.ipautas.data.converter

import androidx.room.TypeConverter
import java.util.*


/**
 * Converter data.
 *
 * @project iPautas
 * @create_at 2019-10-29
 * @author lucasmarciano
 */
class DateConverter {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return (date?.time)
    }
}