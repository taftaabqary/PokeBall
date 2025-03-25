package com.althaaf.pokeball.core.di

import androidx.room.Room
import com.althaaf.pokeball.core.BuildConfig
import com.althaaf.pokeball.core.data.local.UserDao
import com.althaaf.pokeball.core.data.local.UserDatabase
import com.althaaf.pokeball.core.data.local.UserPreferences
import com.althaaf.pokeball.core.data.local.dataStore
import com.althaaf.pokeball.core.data.network.ApiService
import com.althaaf.pokeball.core.domain.repository.IAuthenticationRepository
import com.althaaf.pokeball.core.domain.repository.IPokeBallRepository
import com.althaaf.pokeball.core.domain.usecase.AuthenticationInteractor
import com.althaaf.pokeball.core.domain.usecase.AuthenticationUseCase
import com.althaaf.pokeball.core.domain.usecase.PokeBallInteractor
import com.althaaf.pokeball.core.domain.usecase.PokeBallUseCase
import com.althaaf.pokeball.core.repository.AuthRepository
import com.althaaf.pokeball.core.repository.MainRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory<UserDao> {
        get<UserDatabase>().userDao()
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            "user_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val dataStoreModule = module {
    single {
        UserPreferences(androidContext().dataStore)
    }
}

val repositoryModule = module {
    single<IPokeBallRepository> {
        MainRepository(get())
    }

    single<IAuthenticationRepository> {
        AuthRepository(get())
    }
}

val useCaseModule = module {
    single<AuthenticationUseCase> {
        AuthenticationInteractor(get())
    }

    single<PokeBallUseCase> {
        PokeBallInteractor(get())
    }
}