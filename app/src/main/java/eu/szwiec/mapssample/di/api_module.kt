package eu.szwiec.mapssample.di

import com.squareup.moshi.Moshi
import eu.szwiec.mapssample.api.LiveDataCallAdapterFactory
import eu.szwiec.mapssample.api.RestaurantsAdapter
import eu.szwiec.mapssample.api.ZomatoService
import eu.szwiec.mapssample.di.ApiProperties.ZOMATO_URL
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


val apiModule = module {
    single { createPlacesService() }
}

object ApiProperties {
    const val ZOMATO_URL = "https://developers.zomato.com/api/v2.1/"
}

fun createPlacesService(): ZomatoService {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return Retrofit.Builder()
            .baseUrl(ZOMATO_URL)
            .client(
                    OkHttpClient.Builder().addInterceptor(interceptor).build()
            )
            .addConverterFactory(MoshiConverterFactory.create(
                    Moshi.Builder().add(RestaurantsAdapter()).build()
            ))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ZomatoService::class.java)
}