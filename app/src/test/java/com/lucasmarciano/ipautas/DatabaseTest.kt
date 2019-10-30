package com.lucasmarciano.ipautas

import com.lucasmarciano.ipautas.data.AppDatabase
import com.lucasmarciano.ipautas.data.daos.UserDao
import com.lucasmarciano.ipautas.data.models.User
import com.lucasmarciano.ipautas.injection.databaseModules
import org.junit.Assert
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.concurrent.thread

/**
 * Database tests
 *
 * @project iPautas
 * @create_at 2019-10-28
 * @author lucasmarciano
 */
class DatabaseTest : KoinTest {

    private val userDao: UserDao by inject()
    private val dbInstance: AppDatabase by inject()

    @Test
    fun `should get the same instance of Database when run threads simultaneously`() {
        startKoin {
            listOf(databaseModules)
        }
        repeat(10) {
            thread(start = true) {
                println(dbInstance)
            }
        }
        Thread.sleep(500)
    }

    @Test
    fun `should create a user`() {
        val user = User(name = "Name Test", email = "save@tes.com", password = "123456")
        val id = userDao.save(user)
        checkNotNull(id)
    }

    @Test
    fun `should login`() {
        val user = User(name = "Name Test", email = "login@tes.com", password = "123456")
        userDao.save(user)
        val loggedUser = userDao.logIn(email = user.email, pass = user.password)
        checkNotNull(loggedUser)
    }
}