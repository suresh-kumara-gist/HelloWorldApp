package com.helloworld.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var textView: TextView
    private lateinit var button: Button
    private var clickCount = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        
        textView.text = "Welcome to Hello World App!\nOptimized for Redmi Note 7"
        
        button.setOnClickListener {
            clickCount++
            textView.text = "Hello World! (Clicked $clickCount times)"
        }
    }
}