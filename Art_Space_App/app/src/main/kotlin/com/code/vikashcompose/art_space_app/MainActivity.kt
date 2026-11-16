package com.code.vikashcompose.art_space_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import  androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.code.vikashcompose.art_space_app.ui.theme.ComposeEmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEmptyActivityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtAppLauncher(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtworkButton(
onPrevious: () -> Unit,
onNext: () -> Unit,
modifier: Modifier = Modifier
){
	Row(
			modifier = modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(16.dp)
	){
		Button(
		onClick = onPrevious,
		modifier = Modifier.weight(1f)
		){
		Text("Previous")	
		}
		Button(
		onClick = onNext,
		modifier = Modifier.weight(1f)
		){
		Text("Next")	
		}
	}
}
@Composable
fun ArtworkInfo(
title: String,
artist: String,
year: String,
modifier: Modifier = Modifier
){
	Column(
			modifier = modifier
			.fillMaxWidth()
			.background(MaterialTheme.colorScheme.surfaceVariant)
			.padding(16.dp)
	){
		Text(
		text = title,
		fontSize = 24.sp,
		fontWeight = FontWeight.Light
		
		)
		Text(
		text = buildAnnotatedString{
			withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
				append(artist)
			}
			append("($year)")
		}
		
		)
	}
}

@Composable
fun ArtworkImage(
@DrawableRes image: Int,
description: String,
modifier: Modifier = Modifier
){
	Surface(
	modifier = modifier,
	shadowElevation = 8.dp
	){
		Image(
				painter = painterResource(image),
				contentDescription = description,
				contentScale = ContentScale.Fit,
				modifier = Modifier.padding(24.dp)
		)
	}
}
data class ArtWork(
@DrawableRes val image: Int,
val title: String,
val artist: String,
val year: String
)

@Composable
fun ArtAppLauncher(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Vikash kumar")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun ArtAppLauncherPreview() {
    ComposeEmptyActivityTheme {
        ArtAppLauncher()
    }
}