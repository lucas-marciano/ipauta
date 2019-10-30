package com.lucasmarciano.ipautas.ui.newaccount

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lucasmarciano.ipautas.data.daos.UserDao
import com.lucasmarciano.ipautas.data.models.User
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewUserViewModel(private val dao: UserDao) : ViewModel() {
    val TAG = Logger.tag

    /**
     * Method that save a new user, and send the response in the high order function.
     *
     * @param user User
     * @param responseCreatedUser Unit
     */
    fun save(user: User, responseCreatedUser: (resp: Boolean) -> Unit) {
        if (Logger.DEBUG) Log.d(TAG, "save")
        try {
            GlobalScope.launch(Dispatchers.Main) {
                val id = withContext(Dispatchers.Default) {
                    dao.save(user)
                }
                responseCreatedUser(id != null)
            }
        } catch (e: Exception) {
            if (Logger.DEBUG) Log.e(TAG, "save error: ${e.message}")
            responseCreatedUser(false)
        }

    }
}
