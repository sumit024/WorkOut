package com.app_devs.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_b_m_i_calculator.*
import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class BMICalculatorActivity : AppCompatActivity() {
    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW" // Metric Unit View
        private const val US_UNITS_VIEW = "US_UNIT_VIEW" // US Unit View
    }

    private var currentVisibleView: String =
            METRIC_UNITS_VIEW // A variable to hold a value to make visible a selected view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i_calculator)
        setSupportActionBar(bmiToolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        bmiToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        rgUnits.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbMetricUnits) {
                makeMetricViewVisible()
            } else {
                makeUsViewVisible()
            }
        }
        calculateBtn.setOnClickListener {
            if (currentVisibleView.equals(METRIC_UNITS_VIEW)) {

                val getHeight = height.text.toString()
                val getWeight = weight.text.toString()
                Log.d("HEIGHT", getHeight)
                Log.d("WEIGHT", getWeight)
                if (getHeight.isEmpty()) {
                    height.error = "Field Can't be empty"
                    height.requestFocus()
                } else if (getWeight.isEmpty()) {
                    weight.error = "Weight field can't be empty"
                    weight.requestFocus()
                } else {
                    val heightVal = getHeight.toDouble()
                    val weightVal = getWeight.toDouble()
                    val bmi = String.format("%.2f", weightVal / (heightVal * heightVal)).toDouble()
                    bmiValue.text = bmi.toString()
                    if (bmi >= 25.0) {
                        category.text = "Overweight"
                    } else if (bmi < 18.5) {
                        category.text = "Underweight"
                    } else {
                        category.text = "Normal"
                    }
                    resultViewBMI.visibility = View.VISIBLE
                }
            }
            else {
                val getHeight = usheight.text.toString()
                val getHeightIn = usheightInches.text.toString()
                val getWeight = usWeight.text.toString()

                Log.d("HEIGHT", getHeight)
                Log.d("WEIGHT", getWeight)
                if (getHeight.isEmpty()) {
                    usheight.error = "Height can't be empty"
                    usheight.requestFocus()
                } else if (getWeight.isEmpty()) {
                    usWeight.error = "Weight can't be empty"
                    usWeight.requestFocus()
                }
                else if(getHeightIn.isEmpty())
                {
                    usheightInches.error="Can't be empty"
                    usheightInches.requestFocus()
                }
                else {
                    val heightVal = getHeight.toDouble()
                    val heightInchVal = getHeightIn.toDouble()
                    val weightVal = getWeight.toDouble()
                    val heightValue =
                            heightVal.toFloat()*12 + heightInchVal.toFloat()

                    // This is the Formula for US UNITS result.
                    // Reference Link : https://www.cdc.gov/healthyweight/assessing/bmi/childrens_bmi/childrens_bmi_formula.html
                    val bmi = String.format("%.2f", 703 * (weightVal / (heightValue * heightValue))).toDouble()
                    bmiValue.text = bmi.toString()
                    if (bmi >= 25.0) {
                        category.text = "Overweight"
                    } else if (bmi < 18.5) {
                        category.text = "Underweight"
                    } else {
                        category.text = "Normal"
                    }
                    resultViewBMI.visibility = View.VISIBLE
                }
            }


        }
    }



    private fun makeUsViewVisible() {
        usUnitUI.visibility = View.VISIBLE
        metricUnitUI.visibility = View.GONE
        currentVisibleView = US_UNITS_VIEW // Current View is updated here.
        resultViewBMI.visibility=View.GONE
        height.setText("")
        weight.setText("")


    }

    private fun makeMetricViewVisible() {
        usUnitUI.visibility = View.GONE
        metricUnitUI.visibility=View.VISIBLE
        currentVisibleView = METRIC_UNITS_VIEW // Current View is updated here.
        resultViewBMI.visibility=View.GONE
        usheight.setText("")
        usWeight.setText("")
        usheightInches.setText("")

    }
}