package com.lucasmarciano.ipautas.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User data entity
 *
 * @project iPautas
 * @create_at 2019-10-24
 * @author lucasmarciano
 */

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var email: String,
    var password: String
)