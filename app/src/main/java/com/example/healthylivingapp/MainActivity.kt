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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontStyle
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainScreen()
        }
    }
}

@Preview
@Composable
fun mainScreen(){

    val gradientColors = listOf<Color>(Color(0x5887C2),Color(0xFFFF7700))

    var recipeNameInput by remember {
        mutableStateOf("")
    }

    var urlImageInput by remember {
        mutableStateOf("")
    }

    var recipeList = remember {
        mutableStateListOf<Recipe>()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(colors =gradientColors )
            ),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Healthy Living",
            fontWeight = FontWeight.ExtraBold,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 36.sp,
                shadow = Shadow(
                    color = Color.Yellow
                    ,blurRadius = 30f
                )
            )
        )

        OutlinedTextField(
            label={Text(text = "Recipe name")},
            value = recipeNameInput,
            onValueChange = {recipeNameInput=it},
            placeholder = { Text(text = "Enter Recipe Name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )

        OutlinedTextField(
            label={Text(text = "URL Image")},
            value = urlImageInput,
            onValueChange = {urlImageInput=it},
            placeholder = { Text(text = "Enter the url Image")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        )


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                val newRecipe: Recipe = Recipe(recipeNameInput,urlImageInput)
                recipeList.add(newRecipe)
                urlImageInput=""
                recipeNameInput=""
            }
        )

        {
            Text(text = "Add Recipe")
        }

        LazyColumn(){
            items(items = recipeList){
                recipe -> recipeWidget(recipe)
            }
        }


    }
}

@Composable
fun recipeWidget(currentRecipe:Recipe){
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.padding(2.dp),
            shape = RoundedCornerShape(30.dp)
        ){
            Box(modifier = Modifier
                .height(30.dp)
                .width(300.dp)
                .padding(10.dp)
            ){
                Text(
                    text = currentRecipe.recipeName,
                    fontSize = 24.sp
                )
            }
        }
        
        Card(
            modifier = Modifier.padding(10.dp),
            shape = RoundedCornerShape(30.dp)
        ){

            Box(modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .padding(10.dp)
            )

            {
                AsyncImage(
                    model = currentRecipe.recipeUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                )

            }

        }
    }
    

}


data class Recipe(val recipeName: String,val recipeUrl: String)

