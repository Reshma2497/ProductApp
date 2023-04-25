package com.example.productapp.ui.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.productapp.model.products.ProductsItemModel


class SharedProductViewModel : ViewModel() {
        val selectedProduct = MutableLiveData<ProductsItemModel>()
    }
