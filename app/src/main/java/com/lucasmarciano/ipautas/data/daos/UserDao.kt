package com.lucasmarciano.ipautas.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lucasmarciano.ipautas.data.models.User

/**
 * Class DAO for the model User
 *
 * @project iPautas
 * @create_at 2019-10-26
 * @author lucasmarciano
 */

@Dao
interface UserDao {

    @Insert
    fun save(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    fun getById(id: Int): User

    @Query("SELECT * FROM user WHERE email = :email AND password = :pass")
    fun logIn(email: String, pass: String): User

    @Delete
    fun delete(user: User)
}