package com.example.mycompose.unit_converter_app

import java.util.Locale
import kotlin.math.abs


class UnitItem(
		val name: String,
		val toBase:(Double) -> Double,
		val fromBase:(Double) -> Double
)

class UnitCategory(
		val title: String,
		val units: List<UnitItem>
)
private fun linear(name: String, factor: Double) = UnitItem(
name = name,
toBase = {it * factor}
fromBase = {it / factor}
)

	val categories = listOf(
			UnitCategory(
			"Length",
				listOf(
						linear("Meter",1.0),
						linear("Kilometer",1000.0),
						linear("Centimeter",0.01),
						linear("Millimeter",0.001),
						linear("Mile",1609.344),
						linear("Foot",0.3048),
						linear("Inch",0.0254)
				)
			),
				
	)