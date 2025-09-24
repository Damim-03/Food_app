package com.example.food_app.Activity.DetailFood

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.R

@Composable
fun DescriptionSection(description: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

        // Section title
        Text(
            text = "Details",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Food description
        Text(
            text = description,
            fontSize = 16.sp,
            color = colorResource(R.color.black),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Extra info / promotion
        Text(
            text = "Buy 2 items for free delivery",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black)
        )
    }
}
