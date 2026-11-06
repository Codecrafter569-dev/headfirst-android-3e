package com.example.myempty.spinnertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.LinearLayout
import android.graphics.Color
import kotlin.random.Random
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val layout_1 = findViewById<LinearLayout>(R.id.rootlayout)
          val button_1 = findViewById<Button>(R.id.color_change_button)
          button_1.setOnClickListener{
            val color = Color.rgb(
                Random.nextInt(256),  // red
                Random.nextInt(256),  // green
                Random.nextInt(256)   // blue
            )
            layout_1.setBackgroundColor(color)
          }
          
          
        val findBeer = findViewById<Button>(R.id.find_beer)
        findBeer.setOnClickListener{
          val beerColor = findViewById<Spinner>(R.id.color)
          val color = beerColor.selectedItem
          val brandList = getBeers(color.toString())
          val brand = brandList.reduce{ src , item -> src +"\n"+item}
          val brands = findViewById<TextView>(R.id.brands)
          brands.text = brand
          
          
        }
    }
    fun getBeers(color: String): List<String>{
      return when(color){
        "Light" -> listOf("this is Light ok","Means it has a white color")
        "Amber" -> listOf("this is a Amber ok","The Power of Light")
        "Brown" -> listOf("This is a Brown ok","The power of Dark")
        "Dark" -> listOf("This is a Dark ok", "Means it has a Dark color")
        else -> listOf("My name is Vikash","The power of Sage")
      }
    }
}