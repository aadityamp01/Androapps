package io.henrikhorbovyi.hacktoberfestissues.data.remote

import io.henrikhorbovyi.hacktoberfestissues.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ServiceBuilder {

    companion object {
        inline operator fun <reified S> invoke(baseUrl: String): S {
            val httpClient = buildInterceptors()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(S::class.java)
        }

        fun buildInterceptors(): OkHttpClient {
            /*val authInterceptor = AuthenticationInterceptor(authToken)*/
            val loggerInterceptor = getLoggerInterceptor()
            return OkHttpClient.Builder().apply {
                addInterceptor(loggerInterceptor)
                // if (!interceptors().contains(authInterceptor)) addInterceptor(authInterceptor)
            }.build()
        }

        private fun getLoggerInterceptor(): HttpLoggingInterceptor {
            val level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE

            return HttpLoggingInterceptor().apply { this.level = level }
        }
    }
}
