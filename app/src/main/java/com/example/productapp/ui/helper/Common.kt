package com.example.productapp.ui.helper

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.example.productapp.R
import com.example.productapp.ui.theme.Pink80
import kotlin.math.roundToInt

import  com.example.productapp.ui.theme.RatingBar

@RequiresApi(Build.VERSION_CODES.N)
fun getCurrency(price: Double): String {

    val currency = Currency.getInstance("USD")
    val currencyFormat = NumberFormat.getCurrencyInstance().apply {
        this.currency = currency
    }
    return currencyFormat.format(price)

}

@Composable
fun RatingBar(rating: Float, maxRating: Int = 5) {
    val filledStars = rating.roundToInt()
    val halfFilledStar = (rating - filledStars) >= 0.5f
    val emptyStars = maxRating - filledStars - if (halfFilledStar) 1 else 0

    Row {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = RatingBar,
                modifier = Modifier.size(24.dp)
            )
        }
        if (halfFilledStar) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = RatingBar,
                modifier = Modifier.size(24.dp)
            )
        }
        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

