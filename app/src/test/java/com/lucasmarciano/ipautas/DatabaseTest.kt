package com.lucasmarciano.ipautas

import com.lucasmarciano.ipautas.data.AppDatabase
import com.lucasmarciano.ipautas.data.daos.ScheduleDao
import com.lucasmarciano.ipautas.data.daos.UserDao
import com.lucasmarciano.ipautas.data.models.Schedule
import com.lucasmarciano.ipautas.data.models.User
import com.lucasmarciano.ipautas.injection.databaseModules
import org.junit.Assert
import org.junit.Before
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

    private val dbInstance: AppDatabase by inject()
    private val userDao: UserDao by inject()
    private val scheduleDao: ScheduleDao by inject()

    private val mUser = User(name = "Name Test", email = "login@tes.com", password = "123456")
    private var mId: Long? = null

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

    @Before
    fun createNewUser() {
        mId = userDao.save(mUser)
    }


    @Test
    fun `should create a user`() {
        checkNotNull(mId)
    }

    @Test
    fun `should login`() {
        val loggedUser = userDao.logIn(email = mUser.email, pass = mUser.password)
        checkNotNull(loggedUser)
    }

    @Test
    fun `should create a new schedule`() {
        val schedule = Schedule(
            title = "Title test created",
            miniDescription = "mini",
            description = "description",
            author = mId!!
        )

        val id = scheduleDao.save(schedule)
        checkNotNull(id)
    }

    @Test
    fun `should update a schedule`() {

    }
}