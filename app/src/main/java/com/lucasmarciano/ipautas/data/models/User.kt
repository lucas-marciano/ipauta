package com.lucasmarciano.ipautas.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

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
    var password: String
)