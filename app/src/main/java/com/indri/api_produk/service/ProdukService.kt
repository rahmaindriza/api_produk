package com.indri.api_produk.service

import com.indri.api_produk.model.ResponseProduk
import retrofit2.Call
import retrofit2.http.GET

interface ProdukService {
    @GET("products")//end point
    fun getAllProduk(): Call<ResponseProduk>
}