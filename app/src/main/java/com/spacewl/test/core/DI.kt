package com.spacewl.test.core

import android.app.Application
import com.spacewl.test.data.local.LocalManager
import com.spacewl.test.data.local.LocalManagerImpl
import com.spacewl.test.data.remote.RemoteManager
import com.spacewl.test.data.remote.RemoteManagerImpl
import com.spacewl.test.feature.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object DI {
    private val viewModelModule = module {
        viewModel { MainViewModel(get(), get()) }
    }
    private val managersModule = module {
        single { LocalManagerImpl() as LocalManager }
        single { RemoteManagerImpl() as RemoteManager }
    }

    fun setup(application: Application) {
        startKoin {
            androidContext(application)
            modules(
                arrayListOf(
                    managersModule,
                    viewModelModule
                )
            )
        }
    }
}