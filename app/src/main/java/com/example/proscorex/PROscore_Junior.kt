package com.example.proscorex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.os.CountDownTimer

class PROscore_Junior : AppCompatActivity() {

    private lateinit var  timerTextView: TextView
    private var timer: CountDownTimer? = null
    private var TimeLeftInMillis: Long = 10 * 60 * 1000 //10 Minutes
    private var TimeStarted = false

    private lateinit var ShotClockTextView: TextView
    private var ShotClockTimer: CountDownTimer? = null
    private var ShotClockTimeLeftInMillis: Long = 24000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proscore_junior)  // Ensure you have a corresponding layout file

        //Initialize Fouls
        setupFouls()

        //Initialize Scores
        setupScores()

        //Initialize Timer
        setupTimeControls()

        //Initialize ShotClock
        setupShotClockControls()

        //Start Game Timer
        timerTextView = findViewById(R.id.lbl_GameTime_Value)
    }

    private fun setupFouls() {
        val btn_HomeFoul_Add = findViewById<Button>(R.id.btn_HomeFoul_Add)
        val btn_HomeFoul_Sub = findViewById<Button>(R.id.btn_HomeFoul_Sub)
        val lbl_HomeFoulValue = findViewById<TextView>(R.id.lbl_Home_Foul_Value)
        var int_HomeFoul = lbl_HomeFoulValue.text.toString().toInt()

        btn_HomeFoul_Add.setOnClickListener {
            int_HomeFoul++
            if(int_HomeFoul >= 6){
                int_HomeFoul=6
                lbl_HomeFoulValue.text = "P"
            }else{
                lbl_HomeFoulValue.text = int_HomeFoul.toString()
            }
        }

        btn_HomeFoul_Sub.setOnClickListener {
            int_HomeFoul--
            if(int_HomeFoul <= 0){
                int_HomeFoul=0
                lbl_HomeFoulValue.text = "0"
            }else{
                lbl_HomeFoulValue.text = int_HomeFoul.toString()
            }
        }

        val btn_GuestFoul_Add = findViewById<Button>(R.id.btn_GuestFoul_Add)
        val btn_GuestFoul_Sub = findViewById<Button>(R.id.btn_GuestFoul_Sub)
        val lbl_GuestFoulValue = findViewById<TextView>(R.id.lbl_Guest_Foul_Value)
        var int_GuestFoul = lbl_GuestFoulValue.text.toString().toInt()

        btn_GuestFoul_Add.setOnClickListener {
            int_GuestFoul++
            if(int_GuestFoul >= 6){
                int_GuestFoul=6
                lbl_GuestFoulValue.text = "P"
            }else{
                lbl_GuestFoulValue.text = int_GuestFoul.toString()
            }
        }

        btn_GuestFoul_Sub.setOnClickListener {
            int_GuestFoul--
            if(int_GuestFoul <= 0){
                int_GuestFoul=0
                lbl_GuestFoulValue.text = "0"
            }else{
                lbl_GuestFoulValue.text = int_GuestFoul.toString()
            }
        }
    }

    private fun setupScores() {
        val btn_HomeScore_Add = findViewById<Button>(R.id.btn_HomeScore_Add)
        val btn_HomeScore_Sub = findViewById<Button>(R.id.btn_HomeScore_Sub)
        val lbl_HomeScore_Value = findViewById<TextView>(R.id.lbl_Home_Score_Value)
        var int_HomeScore = lbl_HomeScore_Value.text.toString().toInt()

        btn_HomeScore_Add.setOnClickListener {
            int_HomeScore++
            if(int_HomeScore>=200){
                int_HomeScore=0
            }
            lbl_HomeScore_Value.text = String.format("%02d",int_HomeScore)
        }

        btn_HomeScore_Sub.setOnClickListener {
            int_HomeScore--
            if(int_HomeScore<0){
                int_HomeScore=199
            }
            lbl_HomeScore_Value.text = String.format("%02d",int_HomeScore)
        }

        val btn_GuestScore_Add = findViewById<Button>(R.id.btn_GuestScore_Add)
        val btn_GuestScore_Sub = findViewById<Button>(R.id.btn_GuestScore_Sub)
        val lbl_GuestScore_Value = findViewById<TextView>(R.id.lbl_Guest_Score_Value)
        var int_GuestScore = lbl_GuestScore_Value.text.toString().toInt()

        btn_GuestScore_Add.setOnClickListener {
            int_GuestScore++
            if(int_GuestScore>=200){
                int_GuestScore=0
            }
            lbl_GuestScore_Value.text = String.format("%02d",int_GuestScore)
        }

        btn_GuestScore_Sub.setOnClickListener {
            int_GuestScore--
            if(int_GuestScore<0){
                int_GuestScore=199
            }
            lbl_GuestScore_Value.text = String.format("%02d",int_GuestScore)
        }
    }

    private fun setupTimeControls() {
        val btn_Start = findViewById<Button>(R.id.btn_Start)
        val btn_Pause = findViewById<Button>(R.id.btn_Pause)

        btn_Start.setOnClickListener{
            if(!TimeStarted){
                startTimer(TimeLeftInMillis)
                startShotClock()
                TimeStarted = true
            }
        }
        btn_Pause.setOnClickListener{
            if(TimeStarted){
                pauseTimer()
                pauseShotClock()
                TimeStarted = false
            }
        }
    }
    
    private fun startTimer(timeInMillis: Long) {
        timer = object : CountDownTimer(timeInMillis, 100) { // update every 100ms
            override fun onTick(millisUntilFinished: Long) {
                TimeLeftInMillis = millisUntilFinished
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val millis = (millisUntilFinished % 1000) / 100
                timerTextView.text = String.format("%02d:%02d.%1d", minutes, seconds, millis)
            }

            override fun onFinish() {
                timerTextView.text = "00:00:0"
            }
        }.start()
    }
    
    private fun pauseTimer(){
        timer?.cancel()
    }

    private fun setupShotClockControls() {
        ShotClockTextView = findViewById(R.id.lbl_ShotClock_Value)
        val btnReset24 = findViewById<Button>(R.id.btn_Reset24)
        val btnReset14 = findViewById<Button>(R.id.btn_Reset14)

        btnReset24.setOnClickListener{
            resetShotClock(24000)
        }

        btnReset14.setOnClickListener{
            resetShotClock(14000)
        }
    }

    private fun resetShotClock(timeInMillis: Long){
        ShotClockTimer?.cancel()
        ShotClockTimeLeftInMillis = timeInMillis
        updateShotClockDisplay()
        if(TimeStarted){
            startShotClock()
        }
    }

    private fun startShotClock() {
        ShotClockTimer = object  : CountDownTimer(ShotClockTimeLeftInMillis, 100){
            override fun onTick(millisUntilFinished: Long) {
                ShotClockTimeLeftInMillis = millisUntilFinished
                updateShotClockDisplay()
            }

            override fun onFinish() {
                ShotClockTextView.text = "00.0"
            }
        }.start()
    }

    private fun pauseShotClock(){
        ShotClockTimer?.cancel()
    }


    private fun updateShotClockDisplay() {
        val seconds = (ShotClockTimeLeftInMillis / 1000).toInt()
        val millis = (ShotClockTimeLeftInMillis % 1000 / 100).toInt()
        ShotClockTextView.text= String.format("%02d.%1d", seconds, millis)
    }
    

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel() // Stop the timer to avoid memory leaks
        ShotClockTimer?.cancel()
    }

}