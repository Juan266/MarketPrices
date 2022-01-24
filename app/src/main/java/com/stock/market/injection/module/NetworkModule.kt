package com.stock.market.injection.module

import android.text.TextUtils
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.stock.market.data.remote.services.SharesService
import com.stock.market.utils.SharedPreferenceUtils
import com.stock.market.utils.getBaseURL
import io.reactivex.schedulers.Schedulers
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT_CONNECT = 10
const val TIMEOUT_WRITE = 10
const val TIMEOUT_READ = 30

@JvmField
val NetworkModule = module {

    fun provideApi(retrofit: Retrofit): SharesService {
        return retrofit.create(SharesService::class.java)
    }

    fun provideInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseURL())
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    single { provideApi(get()) }
    single { provideInterface() }

}

//val DEBUG: Boolean = true

fun getOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    //if (BuildConfig.DEBUG) {
    //if (DEBUG) {
        builder.addNetworkInterceptor(StethoInterceptor())
    //}
    builder.addInterceptor(getHeaderInterceptor())
    builder.addInterceptor(getHttpLoggingInterceptor())
    builder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
    builder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
    builder.writeTimeout(TIMEOUT_WRITE.toLong(), TimeUnit.SECONDS)
    return builder.build()
}

private fun addHeaders(request: Request): Headers.Builder {
    val builder = request.headers().newBuilder()
    val token = SharedPreferenceUtils.getTokenLocal()
    if (!TextUtils.isEmpty(token) && !request.url().toString().contains("token")) {
        builder.add("Authorization", String.format("Bearer %s", token!!))
    }
    return builder
        .add("Content-Type", "application/json")
    //.add("X-Env", if (DEBUG) "dev" else "prd")
    //if (BuildConfig.DEBUG) "dev" else "prd")
}

private fun getHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->
        var request = chain.request()
        request = request.newBuilder().headers(addHeaders(request).build()).build()
        chain.proceed(request)
    }
}

private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return httpLoggingInterceptor
}