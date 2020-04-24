package com.dorokhov.soundmix

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var playerFire: MediaPlayer? = null
    var playerRain: MediaPlayer? = null
    var playerTrain: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPlayFireIcon()
        initPlayRainIcon()
        initPlayTrainIcon()
    }

    private fun initPlayFireIcon() {
        buttonStartFirePlay.setOnClickListener {
            playFire()
        }

        buttonStopFirePlay.setOnClickListener {
            stopFire()
        }

        levelFireSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               playerFire?.setVolume(progress.toFloat()/100.toFloat(),progress.toFloat()/100.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
    private fun initPlayRainIcon() {
        buttonStartRainPlay.setOnClickListener {
            startRain()
        }

        buttonStopRainPlay.setOnClickListener {
            stopRain()
        }

        levelRainSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                playerRain?.setVolume(progress.toFloat()/100.toFloat(),progress.toFloat()/100.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
    private fun initPlayTrainIcon() {
        buttonStartTrainPlay.setOnClickListener {
            startTrain()
        }

        buttonStopTrainPlay.setOnClickListener {
            stopTrain()
        }

        levelTRainSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                playerTrain?.setVolume(progress.toFloat()/100.toFloat(),progress.toFloat()/100.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun playFire() {
        if (playerFire == null) {
            playerFire = MediaPlayer.create(this, R.raw.fire)
        }
        playerFire?.isLooping = true
        playerFire?.start()
    }
    private fun stopFire() {
        if (playerFire != null) {
            playerFire?.release()
            playerFire = null
        }
    }

    private fun startRain() {
        if (playerRain == null) {
            playerRain = MediaPlayer.create(this, R.raw.rain)
        }
        playerRain?.isLooping = true
        playerRain?.start()
    }
    private fun stopRain() {
        if (playerRain != null) {
            playerRain?.release()
            playerRain = null
        }
    }

    private fun startTrain() {
        if (playerTrain == null) {
            playerTrain = MediaPlayer.create(this, R.raw.train)
        }
        playerTrain?.isLooping = true
        playerTrain?.start()
    }
    private fun stopTrain() {
        if (playerTrain != null) {
            playerTrain?.release()
            playerTrain = null
        }
    }

    override fun onStop() {
        super.onStop()
        stopFire()
        stopRain()
        stopTrain()
    }
}
