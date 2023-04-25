package com.example.productapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productapp.ui.products.Products
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import com.example.productapp.ui.productdetails.ProductDetails
import com.example.productapp.ui.products.SharedProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val sharedProductViewModel=SharedProductViewModel()
    val navController = rememberNavController()
    val targetScreen = rememberSaveable { mutableStateOf(Screen.Products) }

    Scaffold(topBar = { ProductScreenApp(targetScreen.value, navController) },
        content = {
            NavHost(
                navController = navController, startDestination = Screen.Products.route,
            ) {
                composable(Screen.Products.route) {
                    targetScreen.value = Screen.Products
                    Products(navController = navController,
                        sharedProductViewModel = sharedProductViewModel
                    )
                }
                composable("${Screen.ProductDetails.route}") { backStackEntry ->
                    targetScreen.value = Screen.ProductDetails
                    ProductDetails(sharedProductViewModel)
                }
            }
        })
}

@Composable
fun ProductScreenApp(targetScreen: Screen, navController: NavController)
{
        if (targetScreen != Screen.Products) {
            TopAppBar(
                title = { Text(text = targetScreen.header, color = Color.White,style = MaterialTheme.typography.headlineSmall
                ) },
                navigationIcon = { BackButton(navController = navController) },
                backgroundColor = Color(0xFF03A9F4)
            )
        } else {
            TopAppBar(
                title = { Text(text = targetScreen.header, color = Color.White,style = MaterialTheme.typography.headlineSmall) },
                backgroundColor = Color(0xFF03A9F4)
            )
        }
    }

@Composable
fun BackButton(navController: NavController) {
    IconButton(onClick = { navController.navigateUp() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White
        )
    }
}


