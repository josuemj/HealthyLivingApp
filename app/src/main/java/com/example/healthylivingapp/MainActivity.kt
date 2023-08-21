package com.example.healthylivingapp

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun app(){

    var recipeName = remember {
        mutableStateOf("")}

    var recipeUrl = remember {
        mutableStateOf("")
    }

    var recipeList = mutableListOf<Recipe>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
            
        TextField(
            value = recipeName.value,
            onValueChange = {
                recipeName.value = it
        },
            placeholder = {
                Text(text = "Introduce recipe name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = recipeUrl.value,
            onValueChange = {
                recipeUrl.value = it
            },
            placeholder = {
                Text(text = "Introduce url Image")
            },
            modifier = Modifier.fillMaxWidth()
        )
        
        
        Button(onClick = {
            println("receipt name: ${recipeName.value}+\nURL: ${recipeUrl.value}")
           val newRecipe = Recipe(recipeName.value,recipeUrl.value)
            recipeList.add(newRecipe)
        },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(text = "Add Recipe")
        }

        LazyColumn (

                ){
            items(recipeList) { recipe ->
                    Text(
                        text = recipe.recipeName,
                    )

            }
        }



    }
}

data class Recipe(val recipeName: String,val recipeUrl: String)

