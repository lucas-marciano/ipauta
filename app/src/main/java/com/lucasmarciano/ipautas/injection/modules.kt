package com.lucasmarciano.ipautas.injection

import androidx.room.Room
import com.lucasmarciano.ipautas.data.AppDatabase
import com.lucasmarciano.ipautas.ui.detail.DetailViewModel
import com.lucasmarciano.ipautas.ui.list.ListAdapter
import com.lucasmarciano.ipautas.ui.list.ListViewModel
import com.lucasmarciano.ipautas.ui.login.LoginViewModel
import com.lucasmarciano.ipautas.ui.newaccount.NewUserViewModel
import com.lucasmarciano.ipautas.ui.recover.RecoverViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin modules
 *
 * @project iPautas
 * @create_at 2019-10-24
 * @author lucasmarciano
 */
val viewModelsModules = module {
    viewModel { DetailViewModel() }
    viewModel { ListViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RecoverViewModel() }
    viewModel { NewUserViewModel(get()) }
}

val uiModule = module {
    factory { ListAdapter() }
}

val databaseModules = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "ipauta-database"
        ).build()
    }
    single { get<AppDatabase>().scheduleDao() }
    single { get<AppDatabase>().userDao() }
}
