package com.dorokhov.soundmix.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.dorokhov.soundmix.constants.IntentConstanstsKeys.AUDIO_INTENT_KEY

class SoundPlayService: Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val resourceID = intent?.getIntExtra(AUDIO_INTENT_KEY, -1)

        assert(resourceID != -1)
        mediaPlayer = MediaPlayer.create(this, resourceID!!).apply {
            isLooping = true
            start()
        }

        return START_STICKY
    }


    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}