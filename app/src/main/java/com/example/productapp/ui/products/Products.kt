package com.example.productapp.ui.products

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.productapp.R
import com.example.productapp.model.products.ProductsItemModel
import com.example.productapp.model.products.ProductsModel
import com.example.productapp.ui.errorhandling.ErrorHandling.doIfSuccess
import com.example.productapp.ui.helper.RatingBar
import com.example.productapp.ui.helper.getCurrency
import com.example.productapp.ui.navigation.Screen
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Products(viewModel: ProductsViewModel= hiltViewModel(), navController: NavHostController,sharedProductViewModel:SharedProductViewModel)

{

    //navigation
    val navigateToDetails: (ProductsItemModel) -> Unit = {
        sharedProductViewModel.selectedProduct.value=it
        navController.navigate("${Screen.ProductDetails.route}")}

    // Product list
    val products = remember { mutableStateListOf<ProductsItemModel>() }

    // Filtered product list based on search query
    val searchQuery = remember { mutableStateOf("") }
    val filteredProductsList = if (searchQuery.value.isBlank()) {
        products
    } else {
        products.filter { product ->
            product.title!!.contains(searchQuery.value, ignoreCase = true)
        }
    }
    //View Model Get Producrs
    viewModel.getProducts()


    //Observer the changes for view model
    viewModel.products.observeAsState().value?.doIfSuccess {
        products.addAll(it)
    }


    Scaffold (
//        topBar = { ProductsToolbar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ProductsToolbar()
            SearchBar(searchQuery)

            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp)

            ) {
                itemsIndexed(filteredProductsList) { _, product ->
                    AddProduct(product, navigateToDetails)
                }

            }


        }
    }

}
@Composable
fun ProductsToolbar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.Cyan),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.products).uppercase(Locale.getDefault()),
            //textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(horizontal = 10.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SearchBar(

    searchQuery: MutableState<String>,   modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = {searchQuery.value=it},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
             //MaterialTheme.colorScheme.onPrimary
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    )
}

@Composable
fun AddProduct(model: ProductsItemModel,  onNextClick: (ProductsItemModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable {
                onNextClick(model)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberImagePainter(
                data = model.image
            ),
            contentDescription = null,

            modifier = Modifier
                .size(120.dp)
                .padding(top = 10.dp, bottom = 10.dp)
                ,
            // Add a default image as a fallback
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(5.dp)
                .align(Alignment.Top)
                .weight(2f),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "${model.title}",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${getCurrency(model.price as Double)}",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth().height(45.dp)
            ) {
                RatingBar(
                    model.rating?.rate?.toFloat()!!
                )
                Text(
                    text = " (${model.rating?.rate}) ",
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )


            }


        }
    }
}


