package br.duccipopi.met.model.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val metMuseumRetrofit: Retrofit = Retrofit.Builder()
    .baseUrl(MetMuseumApi.BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

val metMuseumService = metMuseumRetrofit.create(MetMuseumApi::class.java)
