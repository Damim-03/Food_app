package com.example.food_app.Activity.DetailFood

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.R

@Composable
fun FooterSection(
    onAddToCartClick: () -> Unit = {},
    totalPrice: Double = 24.99,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(16.dp)
            .height(60.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.green),
                shape = RoundedCornerShape(50.dp)
            )
            .clickable { onAddToCartClick() } // ✅ fixed
            .padding(horizontal = 8.dp)
    ) {
        // Price bubble
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(100.dp)
                )
        ) {
            Text(
                text = "$${totalPrice.toInt()}", // ✅ better formatting
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                textAlign = TextAlign.Center
            )
        }

        // Add to cart text
        Text(
            text = "Add To Cart",
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.White
        )

        // Cart icon
        Image(
            painter = painterResource(R.drawable.cart),
            contentDescription = "Cart icon",
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(12.dp)
        )
    }
}
