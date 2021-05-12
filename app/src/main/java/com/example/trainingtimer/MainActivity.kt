package com.example.trainingtimer

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timer: Chronometer = findViewById(R.id.timer)
        val startButton: Button = findViewById(R.id.start_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        val interval = Timer()

        startButton.setOnClickListener {
            timer.base = SystemClock.elapsedRealtime()
            timer.start()
            slowTimer(interval)
        }

        stopButton.setOnClickListener {
            timer.stop()
            interval.cancel()
        }
    }

    private fun slowTimer(interval: Timer) {
        interval.schedule(
                timerTask {
                    Log.i("Timer", "4 secs done, speed up!")
                    speedTimer(interval)
                }, 4000
        )
    }

    private fun speedTimer(interval: Timer) {
        Timer().schedule(
                timerTask {
                    Log.i("Timer", "2 secs done, slow down!")
                    slowTimer(interval)
                }, 2000
        )
    }
}