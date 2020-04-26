package com.dorokhov.soundmix.ui

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorokhov.soundmix.R
import com.dorokhov.soundmix.constants.IntentConstanstsKeys
import com.dorokhov.soundmix.constants.IntentConstanstsKeys.sounds
import com.dorokhov.soundmix.models.SoundModel
import com.dorokhov.soundmix.services.SoundPlayService
import com.dorokhov.soundmix.ui.adapters.SoundRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SoundRecyclerViewAdapter.Interaction {

    var adapterSounds: SoundRecyclerViewAdapter? = null
    var playingSounds = HashMap<String, ComponentName?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapterSounds = SoundRecyclerViewAdapter(
            this@MainActivity
        )
        sounds_recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapterSounds
        }

        adapterSounds?.submitList(sounds.toMutableList())
    }

    override fun changeVolume(position: Int, item: SoundModel, newVolume: Int) {

    }

    override fun playSound(position: Int, item: SoundModel) {
        if (!playingSounds.containsKey(item.name)) {
            val intent = Intent(this, SoundPlayService::class.java).apply {
                putExtra(IntentConstanstsKeys.AUDIO_INTENT_KEY, item.soundId)
                putExtra(IntentConstanstsKeys.AUDIO_INTENT_VOLUME_KEY, item.volumeLevel)
                identifier = item.name

            }
            playingSounds[item.name] = startService(intent)
        }
    }

    override fun stopSound(position: Int, item: SoundModel) {
        if (playingSounds.containsKey(item.name)) {
            stopService(playingSounds[item.name])
            playingSounds.remove(item.name)
        }
    }
}
