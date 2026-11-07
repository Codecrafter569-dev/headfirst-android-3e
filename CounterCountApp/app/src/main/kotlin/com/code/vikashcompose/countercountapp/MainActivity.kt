package com.code.vikashcompose.countercountapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.KeyboardType
import com.code.vikashcompose.countercountapp.ui.theme.ComposeEmptyActivityTheme

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
                    
                    CounterScreen()
                }
            }
        }
    }
}


@Composable
fun CounterScreen(modifier: Modifier = Modifier){

	var count by rememberSaveable{ mutableIntStateOf(0) }
	var step by rememberSaveable{ mutableIntStateOf(1)}
	val history  = remember{mutableStateListOf<String>()}
	val message by remember{
		derivedStateOf{
			when{
				count >= 100  -> "🏆 Wow, 100 or more!"
				count >= 10   -> "🎉 Double digits!"
				else -> ""
			}
		}
	}
	
	fun record(text: String){
		history.add(0,text)
		if(history.size > 5) history.removeAt(history.lastIndex)
	}
	
	
	CounterContent(
	count = count,
	step = step,
	message = message,
	history = history,
	onStepChange = { step = it},
	onAdd = {
		count += step
		record("+$step  -> $count")
		
	},
	onSubtract  ={
		count = maxOf(0, count - step)
		record("-$step -> $count")
	},
	onReset  = {
		count = 0 
		history.clear()
	},
	modifier = modifier
	)
}


					@Composable
					fun CounterContent(
							count:Int,
							step:Int,
							message:String,
							history:List<String>,
							onStepChange:(Int) -> Unit,
							onAdd:() -> Unit,
							onSubtract:() -> Unit,
							onReset:() -> Unit,
							modifier: Modifier = Modifier
					){
					
					var text_1 by remember { mutableStateOf("") }   // holds typed value
						Column(
						modifier = modifier
						   .fillMaxSize()
						   .padding(24.dp),
						   verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
						){
							Text(
							text = stringResource(R.string.count_label,count),
							style = MaterialTheme.typography.headlineMedium
							)
							if(message.isNotEmpty()){
								Text(text = message , style = MaterialTheme.typography.titleMedium)
							}
							Spacer(modifier = Modifier.height(24.dp))
							
							Row(
							horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
							){
							Text("Step:")	
								listOf(1,5,10,100).forEach{ option ->
								if(option == step){
									Button(onClick = { onStepChange(option)}){ Text("$option")}
								}else{
									OutlinedButton(onClick  = {onStepChange(option)}){Text("$option")}
								}
							}
						}
						Spacer(modifier = Modifier.height(16.dp))
						
						
						Row(horizontalArrangement = Arrangement.spacedBy(12.dp)){
							Button(onClick = onSubtract , enabled = count > 0){
								Text(stringResource(R.string.button_subtract))
							}
							Button(onClick = onAdd){
								Text(stringResource(R.string.button_add))
							}
							Button(onClick = onReset){
								Text(stringResource(R.string.button_reset))
							}
						}
						
				TextButton(onClick = onReset) {
            Text(stringResource(R.string.button_reset))
        }
        Spacer(modifier = Modifier.height(16.dp))

        history.forEach { entry ->
            Text(text = entry, style = MaterialTheme.typography.bodyMedium)
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