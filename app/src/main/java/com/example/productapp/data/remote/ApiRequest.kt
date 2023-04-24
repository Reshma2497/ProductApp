package com.example.productapp.data.remote


import com.example.productapp.model.products.ProductsModel
import retrofit2.http.GET

interface ApiRequest {
    @GET(ApiDetails.PRODUCTS)
    suspend fun getProducts(): ProductsModel
}