package com.lucasmarciano.ipautas.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lucasmarciano.ipautas.data.models.Schedule

/**
 * Class DAO for the model Schedules
 *
 * @project iPautas
 * @create_at 2019-10-26
 * @author lucasmarciano
 */

@Dao
interface ScheduleDao {
    @Insert
    fun save(user: Schedule): Long

    @Query("SELECT * FROM schedule WHERE author = :id")
    fun getScheduleByAuthor(id: Long): LiveData<MutableList<Schedule>>

    @Delete
    fun delete(user: Schedule)
}