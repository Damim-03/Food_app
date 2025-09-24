package com.example.food_app.Activity.DetailFood

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.food_app.Model.FoodModel
import com.example.food_app.R
import com.example.food_app.helper.ManagmentCart
import com.example.food_app.helper.previewFood

class DetailEachFoodActivity : AppCompatActivity() {

    private lateinit var item: FoodModel
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        item = intent.getSerializableExtra("object") as FoodModel
        item.numberInCart = 1
        managementCart = ManagmentCart(this)

        setContent {
            DetailScreen(
                item = item,
                inBackClick = { finish() },
                onAddToCartClick = {
                    managementCart.insertItem(item)
                }
            )
        }
    }
}

@Composable
fun DetailScreenPreview() {
    DetailScreen(
        item = previewFood,
        inBackClick = {},
        onAddToCartClick = {}
    )
}

@Composable
fun DetailScreen(
    item: FoodModel,
    inBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    var numberInCart by remember { mutableIntStateOf(item.numberInCart) }
    ConstraintLayout {
        val (footer, column) = createRefs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.light_grey))
                .verticalScroll(rememberScrollState())
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
                .padding(bottom = 100.dp)
        ) {
            HeaderSection(
                item = item,
                onBackClick = inBackClick,
            )

            TitleNumberRow(
                item = item,
                numberInCart = numberInCart,
                onIncrement = {
                    numberInCart++
                    item.numberInCart = numberInCart
                },
                onDecrement = {
                    if(numberInCart > 1) {
                        numberInCart--
                        item.numberInCart = numberInCart
                    }
                }
            )

            Text(
                text = "$${item.Price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            RowDetail(item)
            DescriptionSection(item.Description)
            RecommendedList()
        }
        FooterSection(
            onAddToCartClick,
            totalPrice = (item.Price * numberInCart),
            Modifier.constrainAs(footer) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
    }
}