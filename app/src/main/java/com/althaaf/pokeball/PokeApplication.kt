package com.althaaf.pokeball

import android.app.Application
import com.althaaf.pokeball.core.di.dataStoreModule
import com.althaaf.pokeball.core.di.databaseModule
import com.althaaf.pokeball.core.di.networkModule
import com.althaaf.pokeball.core.di.repositoryModule
import com.althaaf.pokeball.di.useCaseModule
import com.althaaf.pokeball.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@PokeApplication)
            loadKoinModules(listOf(
                networkModule,
                databaseModule,
                dataStoreModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            ))
        }
    }
}