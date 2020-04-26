package com.dorokhov.soundmix.constants

import com.dorokhov.soundmix.R
import com.dorokhov.soundmix.models.SoundModel

object IntentConstanstsKeys {

    const val AUDIO_INTENT_KEY = "audio_file_name_intent_key"
    const val AUDIO_INTENT_VOLUME_KEY = "volume_key"

    const val AUDIO_FIRE = R.raw.fire
    const val AUDIO_RAIN = R.raw.rain

    val sounds = arrayOf(
        SoundModel(R.raw.rain, "Дождь", null, 100),
        SoundModel(R.raw.fire, "Огонь", null, 100),
        SoundModel(R.raw.river, "Река", null, 100),
        SoundModel(R.raw.train, "Поезд", null, 100),
        SoundModel(R.raw.wind, "Ветер", null, 50)
    )

}