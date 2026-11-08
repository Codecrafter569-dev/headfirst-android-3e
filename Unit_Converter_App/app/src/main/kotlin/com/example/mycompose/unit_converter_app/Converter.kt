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
				UnitCategory(
						"Weight",
						listOf(
								linear("Kilogram",1.0),
								linear("Gram",0.001),
								linear("Milligram",0.000001),
								linear("Pound",0.45359237),
								linear("Ounce",0.028349523125)
						)
				),
				UnitCategory(
						"Temperature",
						listOf(
								UnitItem("Celsius",{it},{it}),
								UnitItem("Fehrenheit",{(it - 32) * 5 / 9},{it * 9 / 5 + 32}),
								UnitItem("Kelvin",{it - 273.15},{it + 273.15})
						)
				)
	)
	
	fun convert(value: Double, from: UnitItem , to: UnitItem): Double =
	to.fromBase(from.toBase(value))
	
	
	fun formatResult(v: Double): String{
		if(v == 0.0) return "0"
		return if(abs(v) >= 1e9 || abs(v) < 1e-4){
			 String.format(Locale.US , "%.6e", v)
		}else{
			String.format(Locale.US , "%.6f",v).trimEnd('0').trimEnd('.')
		}
	}