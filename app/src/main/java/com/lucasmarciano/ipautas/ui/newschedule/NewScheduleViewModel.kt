package com.lucasmarciano.ipautas.ui.newschedule

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lucasmarciano.ipautas.data.daos.ScheduleDao
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewScheduleViewModel(private val dao: ScheduleDao) : ViewModel() {
    val TAG = Logger.tag

    /**
     * Method that save a new schedule, and send the response in the high order function.
     *
     * @param schedule Schedule
     * @param responseCreated Unit
     */
    fun save(schedule: Schedule, responseCreated: (resp: Boolean) -> Unit) {
        if (Logger.DEBUG) Log.d(TAG, "save")
        try {
            GlobalScope.launch(Dispatchers.Main) {
                val id = withContext(Dispatchers.Default) {
                    dao.save(schedule)
                }
                responseCreated(id != null)
            }
        } catch (e: Exception) {
            if (Logger.DEBUG) Log.e(TAG, "save error: ${e.message}")
            responseCreated(false)
        }

    }
}
