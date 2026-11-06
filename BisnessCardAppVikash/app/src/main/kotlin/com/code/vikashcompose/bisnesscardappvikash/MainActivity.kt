package com.code.vikashcompose.bisnesscardappvikash

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import com.code.vikashcompose.bisnesscardappvikash.ui.theme.ComposeEmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  //  GreetingText("Vikash")
                  BisnessCardApp()
                }
            }
        }
    }
}

@Composable
fun BisnessCardApp(){
  Column(
  modifier = Modifier
  .fillMaxSize()
  .background(Color(0xFF0D47A1)),
  horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
  ){
    Image(
    //81bfba
            imageVector = Icons.Default.Person,
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(
                3.dp,
                Color.Yellow,
                CircleShape,
            )
            .padding(16.dp)
    )
    Spacer(Modifier.padding(16.dp))
    Text(
    text = "Vikash",
    color = Color.White,
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold
    )
    Text(
    text = "Android Developer",
    color = Color(0xFFD9A5F2),
    fontSize = 18.sp
    )
    Text(
    text = stringResource(R.string.text),
    color = Color(0xFFD9A5F2)
    )
    Spacer(Modifier.padding(200.dp))
    ContactInfo()
  }
  
}
@Composable
fun ContactInfo(){
  Column(
  verticalArrangement =
            Arrangement.spacedBy(12.dp)
  ){
    ContectRow(
    Icons.Default.Call,
    "+91 9060858867"
    )
    ContectRow(
    Icons.Default.Email,
    "vikashkumar@gmail.com"
    )
    ContectRow(
    Icons.Default.Place,
    "Jharkhand , Sanidih , Kataa"
    )
    ContectRow(
    Icons.Default.Info,
    "www.com.vikashCodecrafter"
    )
  }
  
}

@Composable
fun ContectRow(
icon: ImageVector,
text: String,
modifier: Modifier = Modifier
){
  Row(
        modifier = modifier,
         verticalAlignment =
            Alignment.CenterVertically
  ){
    Icon(
    imageVector = icon,
    contentDescription = null,
    tint = Color.Yellow
    )
    Spacer(Modifier.padding(16.dp))
    Text(
    text = text,
    color = Color.White,
    fontSize = 16.sp
    )
  }
  
}
@Composable
fun GreetingText(name: String , modifier: Modifier = Modifier){
Column(){
  Text(
  text = "This is new Android Deleoper name is $name",
  modifier = Modifier
  .background(Color.Blue)
  .padding(16.dp)
  )
  Spacer(modifier = Modifier.height(16.dp))
  Text(
  text = "Padding is used to app space in Block of View .",
  modifier = Modifier
    .clip(CircleShape)
    .background(Color.White)
    .padding(20.dp)
  )
  
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hello $name!"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeEmptyActivityTheme {
        Greeting("Android")
    }
}