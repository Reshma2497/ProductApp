package com.example.productapp.ui.productdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.productapp.R
import com.example.productapp.model.products.ProductsItemModel
import com.example.productapp.ui.helper.RatingBar
import com.example.productapp.ui.helper.getCurrency
import com.example.productapp.ui.products.ProductsToolbar
import com.example.productapp.ui.products.SharedProductViewModel
import com.example.productapp.ui.theme.Pink40
import com.example.productapp.ui.theme.Purple80


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(sharedProductViewModel: SharedProductViewModel) {

    val productDetails=sharedProductViewModel.selectedProduct.value!!
    Scaffold(
        topBar = { ProductsToolbar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            ProductDetailsToolbar()

            AddProductDetails(modelDetails = productDetails)
        }
    }
}

@Composable
fun ProductDetailsToolbar(modifier: Modifier = Modifier) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Purple80),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.products_details),
            //textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 10.dp),
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun AddProductDetails(modelDetails: ProductsItemModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = modelDetails.image
            ),
            contentDescription = null,
            modifier = Modifier
                .height(270.dp)
                .width(411.dp)
                .padding(top = 15.dp, bottom = 10.dp)
            // Add a default image as a fallback
        )
        Text(
            text = "${modelDetails.title}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "${getCurrency(modelDetails.price as Double)}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge

        )
       Row() {
           RatingBar(rating = modelDetails.rating?.rate?.toFloat()!!)
           Text(
               text = " (${modelDetails.rating?.rate.toString()}) ${modelDetails.rating.count}",
               style = MaterialTheme.typography.titleLarge
           )
       }

        Text(
            text = "Category",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "${modelDetails.category}",
            modifier = Modifier.padding(start = 10.dp),
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "${modelDetails.description}",
            modifier = Modifier.padding(start = 10.dp),
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.titleSmall
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            onClick = {}
        ) {
            Text(
                text = "Add Cart",
                style = MaterialTheme.typography.titleSmall
            )
        }

    }
}
