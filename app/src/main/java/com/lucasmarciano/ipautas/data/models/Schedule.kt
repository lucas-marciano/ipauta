package com.lucasmarciano.ipautas.data.models

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.*

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
    @ColumnInfo(name = "mini_description")
    var miniDescription: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "is_active")
    var isActive: Boolean = true,
    @ColumnInfo(name = "author")
    var author: Long,
    @ColumnInfo(name = "created_at")
    var createdAt: Date = Date()
)