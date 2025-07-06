package com.example.calc

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val result = findViewById<TextView>(R.id.result)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)

        btnAdd.setOnClickListener {
            val a = num1.text.toString().toDoubleOrNull()
            val b = num2.text.toString().toDoubleOrNull()
            result.text = if (a != null && b != null) "Result: ${a + b}" else "Invalid input"
        }

        btnSub.setOnClickListener {
            val a = num1.text.toString().toDoubleOrNull()
            val b = num2.text.toString().toDoubleOrNull()
            result.text = if (a != null && b != null) "Result: ${a - b}" else "Invalid input"
        }

        btnMul.setOnClickListener {
            val a = num1.text.toString().toDoubleOrNull()
            val b = num2.text.toString().toDoubleOrNull()
            result.text = if (a != null && b != null) "Result: ${a * b}" else "Invalid input"
        }

        btnDiv.setOnClickListener {
            val a = num1.text.toString().toDoubleOrNull()
            val b = num2.text.toString().toDoubleOrNull()
            result.text = if (a != null && b != null && b != 0.0) "Result: ${a / b}" else "Cannot divide by zero"
        }
    }
}
