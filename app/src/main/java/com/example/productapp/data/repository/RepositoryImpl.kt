package com.example.productapp.data.repository

import com.example.productapp.data.remote.ApiRequest
import com.example.productapp.model.products.ProductsModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val apiRequest: ApiRequest
): Repository {
   override suspend fun getProducts() : ProductsModel =apiRequest.getProducts()
}