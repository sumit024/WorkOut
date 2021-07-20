package com.app_devs.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startLayout.setOnClickListener {
            val intent= Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Started",Toast.LENGTH_SHORT).show()
        }
        bmiLayout.setOnClickListener {
            val intent=Intent(this@MainActivity, BMICalculatorActivity::class.java)
            startActivity(intent)
        }
        historyLayout.setOnClickListener {
            val intent=Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }


    }
}