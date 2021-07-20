package com.app_devs.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(historyActivityToolbar)
        val actionBar= supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        historyActivityToolbar.setNavigationOnClickListener {
            onBackPressed()
        }



    }
}