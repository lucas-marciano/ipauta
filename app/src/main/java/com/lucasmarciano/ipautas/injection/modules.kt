package com.lucasmarciano.ipautas.injection

import com.lucasmarciano.ipautas.ui.detail.DetailViewModel
import com.lucasmarciano.ipautas.ui.list.ListViewModel
import com.lucasmarciano.ipautas.ui.login.LoginViewModel
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
val appModule = module {

    // ViewModels
    viewModel { DetailViewModel() }
    viewModel { ListViewModel() }
    viewModel { LoginViewModel() }
    viewModel { RecoverViewModel() }
}
