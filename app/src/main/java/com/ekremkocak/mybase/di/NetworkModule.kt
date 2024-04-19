/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ekremkocak.mybase.di


import android.content.Context
import android.util.Log
import com.ekremkocak.mybase.data.AppApi
import com.ekremkocak.mybase.utilities.Constants
import com.ekremkocak.mybase.utilities.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->

            if (message.length > 4000) {

                val maxLogSize = 2048
                for (i in 0..message.length / maxLogSize) {
                    val start = i * maxLogSize
                    var end = (i + 1) * maxLogSize
                    end = if (end > message.length) message.length else end
                    Log.d("tag_retrofit_intercept", message.substring(start, end))
                }
            } else
                Log.d("tag_retrofit_intercept", message)
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    //token geçerlilik süresi dolarsa 401 dönsün tekrar alacağım
    @Provides
    fun provideAuthorizationInterceptor(@ApplicationContext appContext: Context): Interceptor {
        val token = Prefs.getKeySharedPreferences(
            appContext,
            "Constants.PREF_TOKEN"
        )
        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Constants.CONTENT_TYPE_KEY", "Constants.CONTENT_TYPE_VALUE")
                .addHeader("Constants.HEADER_API_KEY", "Constants.API_TOKEN")
                .addHeader("Constants.AUTHORIZATION_KEY", "token")
                .addHeader("accept", "application/json")
                .build()
            var response = chain.proceed(request)
            if (response.code == 401){
                synchronized(this){
                    response.close()
                    //refresh token yada ne yapılacaksa oçıkış yap mesela
                    //val newToken: String = refreshToken()
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Constants.CONTENT_TYPE_KEY", "Constants.CONTENT_TYPE_VALUE")
                        .addHeader("Constants.HEADER_API_KEY", "Constants.API_TOKEN")
                        .addHeader("Constants.AUTHORIZATION_KEY", "token")
                        .addHeader("accept", "application/json")
                        .build()
                    response = chain.proceed(newRequest)
                }
            }
            response
        }
        return interceptor
    }



    @Provides
    fun providesAppApi(): AppApi {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AppApi::class.java)
    }
}
