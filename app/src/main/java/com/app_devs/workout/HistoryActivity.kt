package com.app_devs.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

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
        getAllCompletedDates()


    }
    private fun getAllCompletedDates()
    {
        val dbHandler=SqliteOpenHelper(this,null)
        val res= dbHandler.getAllCompletedDatesList()
        if(res.size>0)
        {
            tvHistory.visibility= View.VISIBLE
            rvHistory.visibility= View.VISIBLE
            tvNoDataAvailable.visibility=View.GONE

            val adapterHistory:HistoryAdapter=HistoryAdapter(this,res)
            rvHistory.layoutManager=LinearLayoutManager(this)
            rvHistory.adapter=adapterHistory
        }
        else
        {
            tvHistory.visibility= View.GONE
            rvHistory.visibility= View.GONE
            tvNoDataAvailable.visibility=View.VISIBLE
        }

    }
}