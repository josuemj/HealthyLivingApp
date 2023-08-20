package com.example.healthylivingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthylivingapp.ui.theme.HealthyLivingAppTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            app()
        }
    }
}

@Preview
@Composable
fun app() {
    val numbers = listOf<Int>(1,2,43,5,645)
    var itemList = mutableListOf<Recipe>()

    val textState = remember {
        mutableStateOf("")
    }

    val textState_url = remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, Color.Cyan),
                    startY = 0f,
                    endY = 700f
                )
            )
            .verticalScroll(scrollState)

    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            text = "Healthy Living App",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            textAlign = TextAlign.Center,
        )

        Text(
            fontSize = 16.sp,
            text = "Powered by J Industries",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )

//        Image(
//            painter = painterResource(id = R.drawable.blaze), contentDescription = "Product Logo",
//            Modifier
//                .fillMaxWidth()
//                .align(Alignment.CenterHorizontally)
//        )

        TextField(
            value = textState.value, onValueChange = {
                textState.value = it
            },


            placeholder = {
                Text(text = "Introduce Data")
            }, // Text inside the input

            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                .align(Alignment.CenterHorizontally)
        )

        TextField(
            value = textState_url.value, onValueChange = {
                textState_url.value = it
            },


            placeholder = {
                Text(text = "Introduce Data")
            }, // Text inside the input

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Button(

            onClick = {

                //Creating new recipe object
                val newRecipe: Recipe = Recipe(textState.value,textState_url.value)

                //Adding the new recipe to list to show on LazyColumn
                itemList.add(newRecipe)

                for(recipe in itemList){
                    println(recipe.recipeName)
                    println(recipe.urlImage)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .align(Alignment.CenterHorizontally)


        ) {
            Text(text = "Press here")
        }

        //Just leaving as pend the lazyColumn...


    }
}

/**
 * Custom item for lazyColumn
 */
@Composable
fun recipeItem(recipe: Recipe){
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxWidth()
            .padding(30.dp)
    ) {
        Text(
            text = "$recipe.recipeName")

            //Image
    }
}


data class Recipe(val recipeName: String, val urlImage:String)