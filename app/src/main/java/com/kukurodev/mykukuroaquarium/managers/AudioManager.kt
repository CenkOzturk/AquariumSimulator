package com.kukurodev.mykukuroaquarium.managers

import android.media.MediaPlayer
import android.media.SoundPool
import com.kukurodev.mykukuroaquarium.R
import com.kukurodev.mykukuroaquarium.model.SoundEffect
import com.kukurodev.mykukuroaquarium.utils.Utils

object AudioManager {
    private var mediaPlayer: MediaPlayer? = null
    private var soundPool: SoundPool? = null

    private val soundEffects = mutableMapOf<SoundEffect, Int>()

    private var musicEnabled = true
    private var soundEffectsEnabled = true

    fun initialize() {
        if (mediaPlayer != null) return

        val context = Utils.appContext ?: return

        // Background music
        mediaPlayer = MediaPlayer.create(
            context,
            R.raw.game_music
        ).apply {
            isLooping = true
        }

        // Sound effects
        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .build()

        soundEffects[SoundEffect.BUBBLE_POP] =
            soundPool!!.load(
                context,
                R.raw.bubble_pop,
                1
            )
        soundEffects[SoundEffect.COIN_COLLECT] =
            soundPool!!.load(
                context,
                R.raw.coin_collect,
                1
            )
    }

    fun playMusic() {
        if (!musicEnabled) return

        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
            }
        }
    }

    fun pauseMusic() {
        mediaPlayer?.pause()
    }

    fun setMusicEnabled(enabled: Boolean) {
        musicEnabled = enabled

        if (enabled) {
            playMusic()
        } else {
            pauseMusic()
        }
    }

    fun setSoundEffectsEnabled(enabled: Boolean) {
        soundEffectsEnabled = enabled
    }

    fun playEffect(effect: SoundEffect) {
        if (!soundEffectsEnabled) return

        val soundId = soundEffects[effect] ?: return

        soundPool?.play(
            soundId,
            1f,
            1f,
            1,
            0,
            1f
        )
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null

        soundPool?.release()
        soundPool = null

        soundEffects.clear()
    }
}