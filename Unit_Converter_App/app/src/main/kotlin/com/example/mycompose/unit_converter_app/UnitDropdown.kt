package com.example.mycompose.unit_converter_app


import androidx.compose.foundation.clickable                     // makes the invisible layer tappable
import androidx.compose.foundation.layout.Box                     // stacks children on top of each other
import androidx.compose.foundation.layout.fillMaxWidth            // Modifier: take full width
import androidx.compose.material.icons.Icons                      // icon collection holder
import androidx.compose.material.icons.filled.ArrowDropDown      // the small ▼ arrow
import androidx.compose.material3.DropdownMenu                    // the popup list
import androidx.compose.material3.DropdownMenuItem                // one row inside the popup
import androidx.compose.material3.Icon                            // draws an icon
import androidx.compose.material3.OutlinedTextField               // the outlined box
import androidx.compose.material3.Text                            // shows text
import androidx.compose.runtime.Composable                        // marks a UI function
import androidx.compose.runtime.getValue                          // needed for "by" (reading state)
import androidx.compose.runtime.mutableStateOf                    // creates observable state
import androidx.compose.runtime.remember                          // keeps state across recompositions
import androidx.compose.runtime.setValue                          // needed for "by" (writing state)
import androidx.compose.ui.Modifier                               // styling/layout chain


@Composable
fun UnitDropdown(
			label: String,
			items: List<UnitItem>,
			selected: UnitItem,
			onSelected:(UnitItem) -> Unit,
			modifier: Modifier = Modifier
){
	var expanded by remember{ mutableStateOf(false) }
	
	
	Box(modifier = modifier){
		OutlinedTextField(
				value = selected.name,
				onValueChange = {},
				readOnly = true,
				label = { Text(label) },
				trailingIcon = { Icon(Icon.Default.ArrowDropDown , contentDescription = "Open list") },
				modifier = Modifier.fillMaxWidth()
		)
		
		Box(
					 modifier = Modifier
					 .matchParentSize()
					 .clickable{ expanded = true }
		)
		
		
	}
}