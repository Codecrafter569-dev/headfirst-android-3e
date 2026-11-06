package com.Kika.vikashHello.someartical

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image

import com.Kika.vikashHello.someartical.ui.theme.ComposeEmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                /*
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    
                    */
                    
                    PrintArticals(
                    stringResource(R.string.title),
                    stringResource(R.string.text_1),
                    stringResource(R.string.text_2)
                    )
                }
            }
        }
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

@Composable
fun PrintArticals(name: String , text1: String , text2: String , modifier: Modifier = Modifier){
val res1 = painterResource(R.drawable.bg_compose_background)
  Column{
    Image(
    painter = res1,
    contentDescription = null,
    modifier = Modifier
                .fillMaxWidth()
    )
    Text(
    text = name,
    fontSize = 24.sp,
    modifier = Modifier
            .padding(16.dp)
    )
    
    Text(
    text = text1,
    textAlign = TextAlign.Justify,
    modifier = Modifier
            .padding(start=16.dp, end=16.dp)
    )
    Text(
    text = text2,
    textAlign = TextAlign.Justify,
    modifier = Modifier
            .padding(16.dp)
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