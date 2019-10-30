package com.lucasmarciano.ipautas.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lucasmarciano.ipautas.data.daos.UserDao
import com.lucasmarciano.ipautas.data.models.User
import com.lucasmarciano.ipautas.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val dao: UserDao) : ViewModel() {
    val TAG = Logger.tag

    fun logIn(
        email: String,
        pass: String,
        methodResponse: (response: User?) -> Unit
    ) {
        if (Logger.DEBUG) Log.d(TAG, "logIn")

        try {
            GlobalScope.launch(Dispatchers.Main) {
                val currentUser = withContext(Dispatchers.Default) {
                    dao.logIn(email, pass)
                }
                methodResponse(currentUser)
            }
        } catch (e: Exception) {
            if (Logger.DEBUG) Log.e(TAG, "save error: ${e.message}")
            methodResponse(null)
        }

    }
}
