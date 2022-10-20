package com.juanduzac.vendorlust.data.remote.api

import com.juanduzac.vendorlust.data.remote.dtos.VendorsResponseDto
import retrofit2.http.GET

interface VendorsApi {

    @GET("https://gist.githubusercontent.com/F-3r/e7c9c0f5fb3e23b45573c6123499e563/raw/50d7dd2170ad1bd79b964d94567f17187449ea4c/vendorlust.json")
    suspend fun getVendors() : VendorsResponseDto

}