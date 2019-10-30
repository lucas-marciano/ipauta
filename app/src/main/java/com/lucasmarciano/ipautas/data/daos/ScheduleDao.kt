package com.lucasmarciano.ipautas.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
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
    fun save(user: Schedule): Long?

    @Query("SELECT * FROM schedule WHERE author = :id AND is_active = :isActive")
    fun getScheduleByAuthor(
        id: Long,
        isActive: Boolean = true
    ): LiveData<MutableList<Schedule>>?

    @Update
    fun update(vararg schedule: Schedule)

    @Delete
    fun delete(user: Schedule)
}