package com.kveld9.trackgym.ui.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build

enum class TimerSound(val id: String, val displayName: String) {
    DIGITAL_BEEP("DIGITAL_BEEP", "Digital Beep"),
    BOXING_BELL("BOXING_BELL", "Boxing Bell"),
    TING_TING("TING_TING", "Ting-Ting"),
    ALARM("ALARM", "Traditional Alarm"),
    SILENT("SILENT", "Silent / Mute")
}

class WorkoutAudioCuePlayer(private val context: Context) {

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    private var toneGenerator: ToneGenerator? = null
    private var audioFocusRequest: AudioFocusRequest? = null

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 80)
        } catch (_: Exception) {
            // ToneGenerator initialization safety on restricted environments
        }
    }

    private fun requestDuckingFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val playbackAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_EVENT)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            val req = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
                .setAudioAttributes(playbackAttributes)
                .setAcceptsDelayedFocusGain(false)
                .build()
            audioFocusRequest = req
            audioManager?.requestAudioFocus(req)
        } else {
            @Suppress("DEPRECATION")
            audioManager?.requestAudioFocus(
                null,
                AudioManager.STREAM_NOTIFICATION,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
            )
        }
    }

    private fun abandonDuckingFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            audioFocusRequest?.let { audioManager?.abandonAudioFocusRequest(it) }
            audioFocusRequest = null
        } else {
            @Suppress("DEPRECATION")
            audioManager?.abandonAudioFocus(null)
        }
    }

    fun playWarningBeep(sound: TimerSound = TimerSound.DIGITAL_BEEP) {
        if (sound == TimerSound.SILENT) return
        requestDuckingFocus()
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 120)
        } catch (_: Exception) {
        } finally {
            // Abandon after short tone duration
            toneGenerator?.let {
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    abandonDuckingFocus()
                }, 200)
            }
        }
    }

    fun playFinishedSound(sound: TimerSound = TimerSound.DIGITAL_BEEP) {
        if (sound == TimerSound.SILENT) return
        requestDuckingFocus()
        try {
            val toneType = when (sound) {
                TimerSound.DIGITAL_BEEP -> ToneGenerator.TONE_PROP_PROMPT
                TimerSound.BOXING_BELL -> ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE
                TimerSound.TING_TING -> ToneGenerator.TONE_PROP_ACK
                TimerSound.ALARM -> ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK
                TimerSound.SILENT -> ToneGenerator.TONE_PROP_BEEP
            }
            toneGenerator?.startTone(toneType, 400)
        } catch (_: Exception) {
        } finally {
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                abandonDuckingFocus()
            }, 500)
        }
    }

    fun playSetCompleteClick() {
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP2, 60)
        } catch (_: Exception) {
        }
    }

    fun release() {
        abandonDuckingFocus()
        try {
            toneGenerator?.release()
            toneGenerator = null
        } catch (_: Exception) {
        }
    }
}
