package com.example.productapp.data.repository

import com.example.productapp.model.products.ProductsModel


interface Repository {
    suspend fun getProducts(): ProductsModel
}