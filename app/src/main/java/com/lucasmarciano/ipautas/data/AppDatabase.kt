package com.lucasmarciano.ipautas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lucasmarciano.ipautas.data.converter.DateConverter
import com.lucasmarciano.ipautas.data.daos.ScheduleDao
import com.lucasmarciano.ipautas.data.daos.UserDao
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.data.models.User

/**
 * Main class that descript all connections daos for the data base.
 *
 * @project iPautas
 * @create_at 2019-10-26
 * @author lucasmarciano
 */
@Database(entities = [User::class, Schedule::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun scheduleDao(): ScheduleDao
}
