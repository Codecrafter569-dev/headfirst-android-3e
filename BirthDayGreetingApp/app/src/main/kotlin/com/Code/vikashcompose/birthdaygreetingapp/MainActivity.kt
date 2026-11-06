package com.Code.vikashcompose.birthdaygreetingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Code.vikashcompose.birthdaygreetingapp.ui.theme.ComposeEmptyActivityTheme

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
                    
                    BirthDayGreetingApp()
                }
            }
        }
    }
}


@Composable
fun BirthDayGreetingApp(modifier: Modifier = Modifier){
  var name by rememberSaveable{mutableStateOf("Parwati")}
  var celibareting by rememberSaveable{mutableStateOf(false)}
  
  
  Box(modifier = modifier){
    Image(
        painter = painterResource(R.drawable.androidparty),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = 0.6f,
        modifier = Modifier.fillMaxSize()
    )
    GreetingContent(
     name = name,
     celibareting = celibareting,
     onNameChange = {name = it },
     onCelibrateClick = {celibareting = !celibareting },
     modifier = Modifier
     .fillMaxSize()
     .padding(24.dp)
    )
  
  }
  
}

@Composable
fun GreetingContent(
    name:String,
    celibareting:Boolean,
    onNameChange:(String) -> Unit,
    onCelibrateClick:() -> Unit,
    modifier: Modifier = Modifier

){
  val cakeScale by animateFloatAsState(
        targetValue = if (celibareting) 1.5f else 1f,
        label = "cakeScale"
    )
    
    
    Column(
          modifier = modifier,
          verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
      
      Text(
          text = "🎂",
          fontSize = 72.sp,
          modifier = Modifier.scale(cakeScale)
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
          text = stringResource(R.string.happy_birthday_text,name),
          style = TextStyle(
             fontSize = 40.sp,
             lineHeight = 48.sp,
             fontWeight = FontWeight.Bold,
             textAlign = TextAlign.Center
          )
      )
      
      Text(
          text = stringResource(R.string.signature_text,"Your friends"),
          style = TextStyle(fontSize = 22.sp),
          modifier = Modifier
          .padding(top = 16.dp)
          .align(Alignment.End)
      )
      AnimatedVisibility(visible = celibareting){
        
        Text(text="🎉🎈🎉🎈🎉",fontSize= 36.sp)
      }
      Spacer(modifier = Modifier.height(32.dp))
      OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Friend's name") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onCelibrateClick) {
            Text(if (celibareting) "Calm down" else "Celebrate!")
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