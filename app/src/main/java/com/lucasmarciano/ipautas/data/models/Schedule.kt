package com.lucasmarciano.ipautas.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * Schedule data entity
 *
 * @project iPautas
 * @create_at 2019-10-24
 * @author lucasmarciano
 */

@Entity(
    tableName = "schedule",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("author"),
        onDelete = CASCADE
    )]
)
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var author: Int
)