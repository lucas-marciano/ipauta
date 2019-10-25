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
    val STAY_LOGGED_PREF_KEY = "status_stay_logged"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var stayLogged: Boolean
        get() = prefs.getBoolean(STAY_LOGGED_PREF_KEY, false)
        set(value) = prefs.edit().putBoolean(STAY_LOGGED_PREF_KEY, value).apply()
}