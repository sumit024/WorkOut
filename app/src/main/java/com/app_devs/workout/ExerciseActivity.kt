package com.app_devs.workout

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.custom_dialog_layout.*
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity() ,TextToSpeech.OnInitListener{
    private var restTimer:CountDownTimer?=null
    private var restProgress=0

    private var exerciseTimer:CountDownTimer?=null;
    private var exerciseProgress=0;

    private  var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePos=-1

    private var tts:TextToSpeech?=null
    private var player:MediaPlayer?=null

    private var exerciseStatusAdapter:ExerciseStatusAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(exerciseActivityToolbar)
        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        exerciseActivityToolbar.setNavigationOnClickListener{
            customDialogForBackPress()

        }

        tts= TextToSpeech(this,this)

        exerciseList=Constants.defaultExerciseList()
        setRestView()

        setUpExerciseStatusRv()

    }

    override fun onDestroy() {
        restTimer?.cancel()
        restProgress=0
        tts?.stop()
        tts?.shutdown()

        player?.stop()
        super.onDestroy()
    }
    private  fun setRestView()
    {

        restView.visibility=View.VISIBLE
        exerciseView.visibility=View.INVISIBLE

        try {
            player=MediaPlayer.create(applicationContext,R.raw.press_start)
            player!!.isLooping=false
            player!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        restTimer?.cancel()
        restProgress=0

        upComingExercise.text=exerciseList!![currentExercisePos+1].getName()

        setRestProgressBar()
    }
    private fun setRestProgressBar()
    {

        progressBar.progress=restProgress
        restTimer= object : CountDownTimer(1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++;
                progressBar.progress=10-restProgress
                tvTimer.text=(10-restProgress).toString()

            }

            override fun onFinish() {
                currentExercisePos++;
                Toast.makeText(this@ExerciseActivity,"Now we will exercise",Toast.LENGTH_SHORT).show()
                restView.visibility=View.INVISIBLE
                exerciseView.visibility=View.VISIBLE
                exerciseList!![currentExercisePos].setIsSelected(true)
                exerciseStatusAdapter!!.notifyDataSetChanged()

                setExerciseView()

            }
        }.start()
    }
    private fun setExerciseView() {

        exerciseTimer?.cancel()
        exerciseProgress=0
        setExerciseProgress()
        ivImage.setImageResource(exerciseList!![currentExercisePos].getImage())
        tvExerciseName.text=exerciseList!![currentExercisePos].getName()
        speakOut(tvExerciseName.text.toString())

    }
    private fun setExerciseProgress()
    {
        progressBarExercise.progress=exerciseProgress
        exerciseTimer= object :CountDownTimer(1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++;
                progressBarExercise.progress=30-exerciseProgress
                tvTimerExercise.text=(30-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePos<exerciseList!!.size-1)
                {
                    exerciseList!![currentExercisePos].setIsSelected(false)
                    exerciseList!![currentExercisePos].setIsCompleted(true)
                    exerciseStatusAdapter!!.notifyDataSetChanged()


                    setRestView()
                }
                else {
                    //Toast.makeText(this@ExerciseActivity, "Congratulations", Toast.LENGTH_SHORT).show()
                    finish()
                    val intent= Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }



        }.start()
    }

    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS)
        {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }
        }
        else
            Log.e("Status","Initialization Failed")
    }

    private fun speakOut(text:String)
    {
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

    }

    private fun setUpExerciseStatusRv()
    {
        rvExerciseStatus.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseStatusAdapter= ExerciseStatusAdapter(exerciseList!!,this)
        rvExerciseStatus.adapter=exerciseStatusAdapter

    }

    private fun customDialogForBackPress()
    {
        val customDialog=Dialog(this)
        customDialog.setContentView(R.layout.custom_dialog_layout)

        customDialog.tvYes.setOnClickListener {
            restTimer?.cancel()
            restProgress=0
            exerciseTimer?.cancel()
            exerciseProgress=0
            tts?.stop()
            tts?.shutdown()

            player?.stop()
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener {
            customDialog.dismiss()
        }

        customDialog.show()

    }

    


}