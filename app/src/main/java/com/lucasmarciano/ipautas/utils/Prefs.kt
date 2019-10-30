package com.lucasmarciano.ipautas.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreferences main configuration
 *
 * @project iPautas
 * @create_at 2019-10-24
 * @author lucasmarciano
 */

class Prefs(context: Context) {
    val PREFS_FILENAME = "com.lucasmarciano.ipautas.utils.prefs"
    val STAY_LOGGED_PREF_KEY = "STAY_LOGGED_PREF_KEY"
    val USER_LOGGED_ID_PREF_KEY = "USER_LOGGED_ID_PREF_KEY"
    val USER_NAME_PREF_KEY = "USER_NAME_PREF_KEY"
    val USER_EMAIL_PREF_KEY = "USER_EMAIL_PREF_KEY"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var stayLogged: Boolean
        get() = prefs.getBoolean(STAY_LOGGED_PREF_KEY, false)
        set(value) = prefs.edit().putBoolean(STAY_LOGGED_PREF_KEY, value).apply()

    var idUser: Long
        get() = prefs.getLong(USER_LOGGED_ID_PREF_KEY, 0)
        set(value) = prefs.edit().putLong(USER_LOGGED_ID_PREF_KEY, value).apply()

    var userName: String?
        get() = prefs.getString(USER_NAME_PREF_KEY, "")
        set(value) = prefs.edit().putString(USER_NAME_PREF_KEY, value).apply()

    var userEmail: String?
        get() = prefs.getString(USER_EMAIL_PREF_KEY, "")
        set(value) = prefs.edit().putString(USER_EMAIL_PREF_KEY, value).apply()


    fun clearPrefs() {
        stayLogged = false
        idUser = -1
        userName = ""
        userEmail = ""
    }
}