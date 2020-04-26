package com.dorokhov.soundmix.constants

import com.dorokhov.soundmix.R
import com.dorokhov.soundmix.models.SoundModel

object IntentConstanstsKeys {

    const val AUDIO_INTENT_KEY = "audio_file_name_intent_key"
    const val AUDIO_INTENT_VOLUME_KEY = "volume_key"

    const val AUDIO_FIRE = R.raw.fire
    const val AUDIO_RAIN = R.raw.rain

    val sounds = arrayOf(
        SoundModel(R.raw.rain, "Дождь", R.drawable.ic_rain_icon, 100),
        SoundModel(R.raw.fire, "Огонь", R.drawable.ic_fire_icon, 100),
        SoundModel(R.raw.river, "Река", R.drawable.ic_river_icon, 100),
        SoundModel(R.raw.train, "Поезд", R.drawable.ic_train_icon, 100),
        SoundModel(R.raw.wind, "Ветер", R.drawable.ic_wind_icon, 50)
    )

}