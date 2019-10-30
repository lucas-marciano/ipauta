package com.lucasmarciano.ipautas.utils.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function for the Long type
 *
 * @project iPautas
 * @create_at 2019-10-30
 * @author lucasmarciano
 */

fun Date.dateFormat(): String {
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH)
    return format.format(this)
}