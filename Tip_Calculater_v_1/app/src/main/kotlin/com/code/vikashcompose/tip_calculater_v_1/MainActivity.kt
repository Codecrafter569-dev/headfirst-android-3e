package com.code.vikashcompose.tip_calculater_v_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import androidx.compose.material3.Switch
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Alignment
import androidx.annotation.StringRes
import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import com.code.vikashcompose.tip_calculater_v_1.ui.theme.ComposeEmptyActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeEmptyActivityTheme {
                Surface(
                modifier = Modifier.fillMaxSize()
                ){
                	Tip_Calculator()
                }
            }
        }
    }
}

@Composable
fun Tip_Calculator(){
var amountForTip by remember {mutableStateOf("")}
var persentInTip by remember { mutableStateOf("")}
var roundUp by remember { mutableStateOf(false) }
/*
The amountInput initialization can also be written like this with type inference:
var amountInput = mutableStateOf("0")
*/
val tipAmount = persentInTip.toDoubleOrNull() ?: 10.0
val amount = amountForTip.toDoubleOrNull() ?: 0.0
val tip = calculateTip(amount , tipAmount,roundUp)
	Column(
			modifier = Modifier
			.statusBarsPadding()
			.verticalScroll(rememberScrollState())
			.padding(horizontal = 40.dp)
			.safeDrawingPadding(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
	){
		Text(
				text = stringResource(R.string.calculate_tip),
				modifier = Modifier
				   .padding(bottom = 16.dp , top = 40.dp)
				   .align(alignment = Alignment.Start)
		)
		/*
		Tip_Calculator_TextField(
		value = persentInTip,
		onValueChange = { persentInTip = it },
		modifier = Modifier
		.padding(32.dp)
		.fillMaxWidth()
		)
		
		*/
		
		NumberTextField(
		label = R.string.bill_amount,
		leadingIcon = R.drawable.money,
		keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next
    ),
		value = amountForTip,
		onValueChange = {amountForTip = it },
		modifier = Modifier
		    .padding(bottom = 32.dp)
		    .fillMaxWidth()
		    )
		    NumberTextField(
		    label = R.string.how_was_the_service,
		    leadingIcon = R.drawable.percent,
		    keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
		    value = persentInTip,
		    onValueChange = { persentInTip = it },
		    modifier = Modifier
		    			.padding( bottom = 32.dp)
		    			.fillMaxWidth()
		    )
		    RoundTheTipRow(
		    roundUp = roundUp,
		    onRoundUpChanged = { roundUp = it },
		    modifier = Modifier.padding(bottom = 32.dp)
		    )
		Text(
				text = stringResource(R.string.tip_amount,tip),
				style = MaterialTheme.typography.displaySmall
		)
		Spacer(modifier = Modifier.height(150.dp))
	}
}


/*


@Composable
fun Tip_Calculator_TextField(
value: String,
onValueChange:(String) -> Unit,
modifier: Modifier = Modifier){
	TextField(
	value = value,
	label = {Text(stringResource(R.string.tip_lable))},
	singleLine = true,
	keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
	onValueChange  = onValueChange,
	modifier  = modifier
	)
	
}

*/

@Composable
fun NumberTextField(
@StringRes label: Int,
@DrawableRes leadingIcon: Int,
keyboardOptions: KeyboardOptions,
value: String,
onValueChange:(String) -> Unit,
modifier: Modifier = Modifier
){

	TextField(
	value = value,
leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) },
	singleLine = true,
	keyboardOptions = keyboardOptions,
	onValueChange = onValueChange,
	
	modifier = modifier
	)
}

@Composable
fun RoundTheTipRow(
roundUp: Boolean,
onRoundUpChanged: (Boolean) -> Unit,
modifier:Modifier = Modifier
){
	Row(
	modifier = Modifier
	.fillMaxWidth()
	.size(48.dp),
	verticalAlignment = Alignment.CenterVertically
	){
		Text(text = stringResource(R.string.round_up_tip))
		Switch(
				checked = roundUp,
				onCheckedChange = onRoundUpChanged,
				modifier = Modifier
				.fillMaxWidth()
				.wrapContentWidth(Alignment.End),
		)
	}
}


private fun calculateTip(
amount: Double , 
tipPercent: Double = 10.0,
roundUp: Boolean
): String{
	var tip = tipPercent / 100 * amount
	if(roundUp){
		tip = kotlin.math.ceil(tip)
	}
	return NumberFormat.getCurrencyInstance().format(tip)
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