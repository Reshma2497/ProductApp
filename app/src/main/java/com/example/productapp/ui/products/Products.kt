package com.example.productapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.productapp.R
import com.example.productapp.model.products.ProductsItemModel
import com.example.productapp.model.products.ProductsModel
import com.example.productapp.ui.errorhandling.ErrorHandling.doIfSuccess
import com.example.productapp.ui.helper.getCurrency
import com.example.productapp.ui.products.ProductsViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Products(viewModel: ProductsViewModel=hiltViewModel())

{
    //View Model Get Producrs
    viewModel.getProducts()

    var productsList=ProductsModel()

    //Observer the changes for view model
    viewModel.products.observeAsState().value?.doIfSuccess {
        productsList.addAll(it)
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ProductsToolbar()
        Divider()
        SearchBar()

         //get products

//        val products = listOf(
//            ProductsItemModel(
//                id = 1,
//                title = "Widget",
//                description = "A high-quality widget that does amazing things.",
//                price = 19.99,
//                image = "https://example.com/widget.jpg"
//            ),
//            ProductsItemModel(
//                id = 2,
//                title = "Gizmo",
//                description = "A cutting-edge gizmo that will revolutionize your life.",
//                price = 29.99,
//                image = "https://example.com/gizmo.jpg"
//            )
//        )
//

//        val products_sample = ProductsModel()
//        products_sample.addAll(products)
        LazyColumn (
            modifier = Modifier
                .padding(top = 10.dp)
        ){
            itemsIndexed(productsList) { _, product->
                AddProduct(product)
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
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
             MaterialTheme.colorScheme.onPrimary
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
fun AddProduct(model: ProductsItemModel) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
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
                text = "${getCurrency(model.price as Double)}"
            )
            Text(
                text = "${model.rating}"
            )

        }
    }
}