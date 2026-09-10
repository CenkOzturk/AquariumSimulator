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

    fun initialize() {
        val context = Utils.appContext ?: return

        // Background music
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(
                context,
                R.raw.game_music
            ).apply {
                isLooping = true
            }
        }

        // Sound effects
        if (soundPool == null) {
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

        setMusicEnable(GameManager.state.musicEnable)
        setSoundEffectsEnable(GameManager.state.soundEffectEnable)
    }

    fun playMusic() {
        if (!GameManager.state.musicEnable) return

        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
            }
        }
    }

    fun pauseMusic() {
        mediaPlayer?.pause()
    }

    fun setMusicEnable(enabled: Boolean) {
        setStatusMusic(enabled)

        if (enabled) {
            playMusic()
        } else {
            pauseMusic()
        }
    }

    fun setSoundEffectsEnable(enabled: Boolean) {
        setStatusSoundEffect(enabled)
    }

    fun playEffect(effect: SoundEffect) {
        if (!GameManager.state.soundEffectEnable) return

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

    fun setStatusMusic(enabled: Boolean) {
        GameManager.update {
            it.copy(
                musicEnable = enabled
            )
        }
    }

    fun setStatusSoundEffect(enabled: Boolean) {
        GameManager.update {
            it.copy(
                soundEffectEnable = enabled
            )
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null

        soundPool?.release()
        soundPool = null

        soundEffects.clear()
    }
}