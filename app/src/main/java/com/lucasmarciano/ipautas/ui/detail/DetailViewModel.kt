package com.lucasmarciano.ipautas.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lucasmarciano.ipautas.data.daos.ScheduleDao
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val dao: ScheduleDao) : ViewModel() {
    private val TAG = Logger.tag

    var mSchedule: LiveData<Schedule>? = null

    /**
     * Method to get a schedule by ID.
     *
     * @param id Long
     */
    fun getSchedule(id: Long) {
        if (Logger.DEBUG) Log.d(TAG, "getSchedule")

        mSchedule = try {
            dao.getById(id)
        } catch (e: Exception) {
            if (Logger.DEBUG) Log.e(TAG, "getListSchedules error: ${e.message}")
            null
        }
    }

    /**
     * Method that update the status of the current schedule
     *
     * @param schedule Schedule
     * @param responseMethod Unit
     */
    fun updateSchedule(schedule: Schedule, responseMethod: (resp: Boolean) -> Unit) {
        if (Logger.DEBUG) Log.d(TAG, "updateSchedule")

        try {
            GlobalScope.launch(Dispatchers.Main) {
                val rows = withContext(Dispatchers.Default) {
                    dao.update(schedule)
                }
                responseMethod(rows > 0)
            }
        } catch (e: Exception) {
            if (Logger.DEBUG) Log.e(TAG, "update error: ${e.message}")
            responseMethod(false)
        }
    }
}
