package com.example.timer

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class TimerViewModel() : ViewModel() {

    companion object{
        private const val  DONE = 0L

        // This is the number of milliseconds in a second
        private const val ONE_SECOND = 1000L

        // This is the total time


    }
    private var  COUNTDOWN_TIME = 1000L



    private lateinit var timer: CountDownTimer



    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _currentState = MutableLiveData<Boolean>()
    val currentState: LiveData<Boolean>
        get() = _currentState


    init {

        _currentTime.value=0
        _currentState.value=true


        //timer.start()
    }
    fun timer(){

        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
                Log.i("hello","time value ${(millisUntilFinished / ONE_SECOND)}")
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _currentState.value=false

            }

        }
        timer.start()
    }
    fun startTimer(time: Int){
        COUNTDOWN_TIME =ONE_SECOND*time*60
        timer()
        _currentState.value=true

        Log.i("hello","starttimer called$COUNTDOWN_TIME")
        Log.i("hello","currenttime value ${currentTime.value}")


    }
    fun stopTimer(){
        timer.cancel()
        Log.i("hello","stoptimer called$COUNTDOWN_TIME")
    }

}