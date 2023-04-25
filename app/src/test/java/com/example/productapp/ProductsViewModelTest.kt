package com.example.productapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.productapp.data.repository.Repository
import com.example.productapp.model.products.ProductsItemModel
import com.example.productapp.model.products.ProductsModel
import com.example.productapp.ui.errorhandling.ErrorHandling.doIfSuccess
import com.example.productapp.ui.products.ProductsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class ProductsViewModelTest {

    private val testDispatcher= StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUP()
    {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
     fun testGetProducts_emptyResponse() = runTest {
        val products = ProductsModel()

        // Return the mock ProductsModel object from the repository
        Mockito.`when`(repository.getProducts()).thenReturn(products)

        val viewModel= ProductsViewModel(repository)
        viewModel.getProducts()

        // Call the function that uses the repository to get the products

        testDispatcher.scheduler.advanceUntilIdle()

        // Check that the products LiveData contains the expected result
        viewModel.products.value?.doIfSuccess { value ->
         assertEquals(0,value.size)
        }
        //assertTrue(result is ResultOf.Success)
        //assertTrue(true)
        //assertEquals(products, (result as ResultOf.Success).value)
    }



    @Test
     fun `getProducts should post Success with valid response`() = runTest {
        // Arrange
        val products = listOf(
            ProductsItemModel(
                id = 1,
                title = "Widget",
                description = "A high-quality widget that does amazing things.",
                price = 19.99,
                image = "https://example.com/widget.jpg"
            ),
            ProductsItemModel(
                id = 2,
                title = "Gizmo",
                description = "A cutting-edge gizmo that will revolutionize your life.",
                price = 29.99,
                image = "https://example.com/gizmo.jpg"
            )
        )
        val products_sample = ProductsModel()
        products_sample.addAll(products)

        Mockito.`when`(repository.getProducts()).thenReturn(products_sample)

        // Return the mock ProductsModel object from the repository
        val viewModel=ProductsViewModel(repository)
        viewModel.getProducts()
        // Call the function that uses the repository to get the products

        testDispatcher.scheduler.advanceUntilIdle()


        // Check that the products LiveData contains the expected result
        viewModel.products.value?.doIfSuccess { value ->
            assertEquals(2,value.size)
        }
    }




//    @Test
//    fun getRepository() {
//    }
}