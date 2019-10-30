package com.lucasmarciano.ipautas.utils.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function for the String type
 *
 * @project iPautas
 * @create_at 2019-10-29
 * @author lucasmarciano
 */

fun String.dateFormat(): String {
    val date = SimpleDateFormat("yyyy-MM-dd HH:ii:ss", Locale.getDefault()).parse(this)
    return SimpleDateFormat("dd/MM/yyyy HH:ii", Locale.getDefault()).format(date!!)
}
 