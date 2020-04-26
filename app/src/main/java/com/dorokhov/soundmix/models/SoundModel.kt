package com.dorokhov.soundmix.models

data class SoundModel(
    var soundId: Int,
    var name: String,
    val image: String?,
    var volumeLevel: Int
)