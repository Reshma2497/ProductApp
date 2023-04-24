package com.example.productapp.ui.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productapp.data.repository.Repository
import com.example.productapp.model.errorhandling.ResultOf
import com.example.productapp.model.products.ProductsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val products=MutableLiveData<ResultOf<ProductsModel>>()


    fun getProducts()
    {
        viewModelScope.launch {
            try {
                val result=repository.getProducts()
                products.postValue(ResultOf.Success(result))
            }catch (ioe:IOException)
            {
                products.postValue(ResultOf.Failure("[IO] error please retry",ioe))
            }catch (he: HttpException)
            {
                products.postValue(ResultOf.Failure("[HTTP] error please retry",he))
            }
        }
    }
}