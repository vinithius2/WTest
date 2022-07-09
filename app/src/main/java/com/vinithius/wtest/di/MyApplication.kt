package com.vinithius.wtest.di

import com.vinithius.wtest.datasource.database.AppDatabase
import com.vinithius.wtest.datasource.repository.WTestRemoteDataResource
import com.vinithius.wtest.datasource.repository.WTestRepository
import com.vinithius.wtest.ui.WTestViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val repositoryModule = module {
    single { get<Retrofit>().create(WTestRemoteDataResource::class.java) }
}

val repositoryDataModule = module {
    single { WTestRepository(get(), get()) }
}

val viewModelModule = module {
    single { WTestViewModel(get()) }
}

val networkModule = module {
    single { retrofit() }
}

val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).codigoPostalDao() }
}

fun retrofit(): Retrofit {

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()

    return Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/centraldedados/codigos_postais/master/data/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
