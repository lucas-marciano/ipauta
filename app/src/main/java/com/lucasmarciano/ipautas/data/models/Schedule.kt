package com.lucasmarciano.ipautas.data.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * Schedule data entity
 *
 * @project iPautas
 * @create_at 2019-10-24
 * @author lucasmarciano
 */

@Entity(
    tableName = "schedule",
    indices = [Index(value = arrayOf("author"))],
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("author"),
        onDelete = CASCADE
    )]
)
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "author")
    var author: Long
)