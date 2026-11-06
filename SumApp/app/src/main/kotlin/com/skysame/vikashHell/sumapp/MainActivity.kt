package com.skysame.vikashHell.sumapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val et_1 = findViewById<EditText>(R.id.edit_1)
        val btn_1 = findViewById<Button>(R.id.btn_1)
        val tv_1 = findViewById<TextView>(R.id.textview_1)
        btn_1.setOnClickListener{
          tv_1.text ="This is the Sum: \n ${et_1.text.toString().trim()}" 
        }
    }
}