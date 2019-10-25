package com.lucasmarciano.ipautas.injection

import android.app.Application
import com.lucasmarciano.ipautas.utils.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Base application
 *
 * @project iPautas
 * @create_at 2019-10-24
 * @author lucasmarciano
 */

val prefs: Prefs by lazy {
    MyApplication.prefs!!
}

open class MyApplication: Application() {

    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}