package com.hello.vikashcompose.dicerollerapp

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Spacer

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.wrapContentSize
import com.hello.vikashcompose.dicerollerapp.ui.theme.ComposeEmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEmptyActivityTheme {
                DiceRollerApp()
            }
        }
    }
}


@Composable
fun DiceRollerApp(){
  DiceWithButtonImage(modifier = Modifier
  .fillMaxSize()
  .wrapContentSize(Alignment.Center)
  )
}

@Composable
fun DiceWithButtonImage(modifier: Modifier = Modifier){
var result by remember { mutableStateOf(1) }
val imageResaurces = when(result){
  1 -> R.drawable.dice_1
  2 -> R.drawable.dice_2
  3 -> R.drawable.dice_3
  4 -> R.drawable.dice_4
  5 -> R.drawable.dice_5
  else -> R.drawable.dice_6
}
  Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
      painter = painterResource(imageResaurces),
      contentDescription = result.toString()
      )
      Spacer(modifier = Modifier.height(16.dp))
      Button(onClick = {result = (1..6).random()}){
        Text(text = stringResource(R.string.roll))
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeEmptyActivityTheme {
        Greeting("Android")
    }
}