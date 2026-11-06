package com.code.vikashcompose.bisnesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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


import com.code.vikashcompose.bisnesscardapp.ui.theme.ComposeEmptyActivityTheme

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
                   BusinessCard()
                   
                }
            }
        }
    }
}
@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD2E8D4)),
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        // Top: logo + name + title
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement =
                Arrangement.Center,
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    R.drawable.android_logo
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Color(0xFF073042)
                    )
            )
            Text(
                text = "Jennifer Doe",
                fontSize = 36.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = "Android Developer " +
                    "Extraordinaire",
                color = Color(0xFF006D3B),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        // Bottom: contact info
        Column(
            modifier = Modifier
                .padding(bottom = 32.dp)
        ) {
            ContactRow(
                Icons.Default.Call,
                "+11 (123) 444 555 666"
            )
            ContactRow(
                Icons.Default.Share,
                "@AndroidDev"
            )
            ContactRow(
                Icons.Default.Email,
                "jen.doe@android.com"
            )
        }
    }
}

@Composable
fun ContactRow(
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF006D3B)
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 16.dp),
            fontSize = 14.sp
        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize(),
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