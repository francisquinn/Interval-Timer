package com.example.trainingtimer

import android.media.AudioManager
import android.media.ToneGenerator
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
        val tone = ToneGenerator(AudioManager.STREAM_ALARM, 100)

        startButton.setOnLongClickListener {
            tone.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD)
            timer.base = SystemClock.elapsedRealtime()
            timer.start()
            slowTimer(interval, tone)
            true
        }

        stopButton.setOnLongClickListener {
            interval.cancel()
            timer.stop()
            true
        }
    }

    private fun slowTimer(interval: Timer, tone: ToneGenerator) {
        interval.schedule(
                timerTask {
                    tone.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL, 200)
                    speedTimer(interval, tone)
                }, 240000
        )
    }

    private fun speedTimer(interval: Timer, tone: ToneGenerator) {
        interval.schedule(
                timerTask {
                    tone.startTone(ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL, 200)
                    slowTimer(interval, tone)
                }, 120000
        )
    }
}