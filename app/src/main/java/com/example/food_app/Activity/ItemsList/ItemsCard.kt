package com.example.food_app.Activity.ItemsList

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.food_app.Activity.DetailFood.DetailEachFoodActivity
import com.example.food_app.Activity.DetailFood.DetailScreenPreview
import com.example.food_app.Model.FoodModel
import com.example.food_app.R
import com.example.food_app.helper.previewFood
import com.example.food_app.ui.theme.darkPurple

@Composable
fun ItemsList(items: List<FoodModel>) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        itemsIndexed(items) { index, item ->
            Items(item = item)
        }
    }
}

@Composable
fun ItemsListPreview() {
    val sample = listOf(previewFood, previewFood)
    ItemsList(items = sample)
}

@Composable
fun Items(item: FoodModel) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.white),
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                val intent = Intent(context, DetailEachFoodActivity::class.java).apply {
                    putExtra("object", item)
                }
                startActivity(context, intent, null)
            }
            .padding(8.dp)
    ) {
        FoodImage(item = item)
        FoodDetail(item = item)
    }
}

@Composable
fun RowScope.FoodDetail(item: FoodModel) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
            .weight(1f)
    ) {
        Text(
            text = item.Title,
            color = darkPurple,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )

        TimingRow(item.TimeValue)
        RatingBarRow(item.Star)
        PriceRow(item.Price)
    }
}

@Composable
fun PriceRow(price: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Text(
            text = "$$price",
            color = darkPurple,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "+ Add",
            color = colorResource(R.color.white),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = colorResource(R.color.green),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun RatingBarRow(star: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.star),
            contentDescription = "Rating star",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "$star",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun TimingRow(timedValue: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.time),
            contentDescription = "Preparation time",
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = "$timedValue min",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun FoodImage(item: FoodModel) {
    AsyncImage(
        model = item.ImagePath,
        contentDescription = item.Title,
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop
    )
}
