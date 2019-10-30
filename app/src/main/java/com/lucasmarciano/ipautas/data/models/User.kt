package com.lucasmarciano.ipautas.data.models

import androidx.room.*
import com.lucasmarciano.ipautas.utils.ext.dateFormat
import java.util.*

/**
 * User data entity
 *
 * @project iPautas
 * @create_at 2019-10-24
 * @author lucasmarciano
 */

@Entity(
    tableName = "user",
    indices = [Index(
        value = ["email"],
        unique = true
    )]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "created_at")
    var createdAt: String = Date().dateFormat()
)