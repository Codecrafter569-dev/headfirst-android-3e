package com.kanuna.vikash.darkandlight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val root = findViewById<LinearLayout>(R.id.root)
        val title = findViewById<TextView>(R.id.title)
        val themeSwitch = findViewById<SwitchCompat>(R.id.themeSwitch)
        
        
        themeSwitch.setOnCheckedChangeListener{ _ , isChecked ->
        
        if(isChecked){
          root.setBackgroundColor(ContextCompat.getColor(this,R.color.dark_bg))
          title.setTextColor(ContextCompat.getColor(this,R.color.dark_text))
          themeSwitch.setTextColor(ContextCompat.getColor(this,R.color.dark_text))
          title.text = "Dark mode is ON !"
        }else{
          root.setBackgroundColor(ContextCompat.getColor(this,R.color.light_bg))
          title.setTextColor(ContextCompat.getColor(this,R.color.light_text))
          themeSwitch.setTextColor(ContextCompat.getColor(this,R.color.light_text))
          title.text = "Light mode is ON !"
        }
        
        }
    }
}