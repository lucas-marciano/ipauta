package com.lucasmarciano.ipautas.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucasmarciano.ipautas.data.daos.ScheduleDao
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel(private val dao: ScheduleDao) : ViewModel() {
    val TAG = Logger.tag

    var listOfSchedule: LiveData<MutableList<Schedule>>? = null

    /**
     * Method to get a list of the Schedules by author and if is active or not.
     *
     * @param userId Long
     * @param isActive Boolean
     */
    fun getListSchedules(userId: Long, isActive: Boolean) {
        if (Logger.DEBUG) Log.d(TAG, "getListSchedules")

        listOfSchedule = try {
            dao.getScheduleByAuthor(userId, isActive)
        } catch (e: Exception) {
            if (Logger.DEBUG) Log.e(TAG, "getListSchedules error: ${e.message}")
            null
        }

    }

}
