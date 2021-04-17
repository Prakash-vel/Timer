package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.timer.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {
            val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

            val TimerViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
            binding.stopButton.setEnabled(false)
            binding.startButton.setOnClickListener {
                try {
                    Log.i("hello", "startbutton clicked$")
                    val time: Int = Integer.parseInt(binding.editTextTime.text.toString())
                    Log.i("hello", "startbutton clicked$time")
                    TimerViewModel.startTimer(time)
                    it.setEnabled(false)
                    binding.editTextTime.setVisibility(View.GONE)
                    binding.stopButton.setEnabled(true)

                }catch(e:Exception){
                    Log.i("hello","startbutton error ${e}")
                }
            }
            binding.stopButton.setOnClickListener {
                Log.i("hello","stopbutton clicked$")
                TimerViewModel.stopTimer()
                it.setEnabled(false)
                binding.editTextTime.setVisibility(View.VISIBLE)
                binding.startButton.setEnabled(true)

            }
            TimerViewModel.currentTime.observe(this, Observer {
                binding.textView.text = DateUtils.formatElapsedTime(TimerViewModel.currentTime.value!!).toString()
                //Log.i("hello","observer${TimerViewModel.currentTime.value} ")
            })
            TimerViewModel.currentState.observe(this, Observer {
                if(it == false){
                    binding.editTextTime.setVisibility(View.VISIBLE)
                    binding.startButton.setEnabled(true)
                    binding.stopButton.setEnabled(false)
                }

            })
        }catch(e:Exception){
            Log.i("hello","error $e")
        }
    }
}