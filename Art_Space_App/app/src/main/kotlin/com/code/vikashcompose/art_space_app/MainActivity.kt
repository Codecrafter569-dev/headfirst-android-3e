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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
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
                    ArtSpaceScreen(
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
about: String,
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
		fontWeight = FontWeight.Bold
		
		)
		Text(
		text = about,
		fontSize = 15.sp,
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
val about: String,
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
@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier){
	val artworks = listOf(
	ArtWork(R.drawable.art_1,"Energy Conservation and a Sustainable Future","This poster shows Mother Earth holding a cracked, wounded globe with a tree growing from it. Behind her, factories spew fire and smoke and glowing skyscrapers burn power, while a broken bulb lies beside a hand lighting a candle. A lone wind turbine stands out against the stormy sky. It warns that wasting energy and burning fossil fuels harms the planet, and it urges us to choose clean, renewable energy to protect the future.","Khushi kumari","2020"),
	ArtWork(R.drawable.art_2,"Protecting the Earth","Two open hands gently hold the Earth, which is covered with green trees, against a night sky. City buildings stand in the background. The painting shows that the planet's survival is in human hands. Development and cities should not destroy nature, and every person has a duty to care for, protect and preserve the environment for future generations.","Khushi kumari","2021"),
	ArtWork(R.drawable.art_3,"Don't Burn the Future for a Light Today!","This poster shows Mother Earth holding a cracked, wounded globe with a tree growing from it. Behind her, factories spew fire and smoke and glowing skyscrapers burn power, while a broken bulb lies beside a hand lighting a candle. A lone wind turbine stands out against the stormy sky. It warns that wasting energy and burning fossil fuels harms the planet, and it urges us to choose clean, renewable energy to protect the future.","Khushi kumari","2023")
	
	)
	
	var current by rememberSaveable{ mutableIntStateOf(0)}
	val art = artworks[current]
	
	Column(
	 modifier = modifier
	 .fillMaxSize()
	 .padding(16.dp),
	 horizontalAlignment = Alignment.CenterHorizontally
	){
		Box(
		modifier = Modifier
		.weight(1f)
		.fillMaxWidth(),
		contentAlignment = Alignment.Center
		){
			ArtworkImage(image = art.image,description = art.title)
		}
		
		Spacer(modifier = Modifier.height(16.dp))
		ArtworkInfo(title = art.title ,about = art.about, artist = art.artist , year = art.year)
		
		Spacer(modifier = Modifier.height(16.dp))
		
		ArtworkButton(
		onPrevious  = {current = (current - 1 + artworks.size) % artworks.size },
		onNext  = {current = (current + 1) % artworks.size}
		)
	}
}
@Preview(showBackground = true)
@Composable
fun ArtAppLauncherPreview() {
    ComposeEmptyActivityTheme {
        ArtAppLauncher()
    }
}