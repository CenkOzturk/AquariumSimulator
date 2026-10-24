package com.kukurodev.mykukuroaquarium.utils

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.google.gson.Gson
import com.kukurodev.mykukuroaquarium.managers.GameAlertManager
import com.kukurodev.mykukuroaquarium.managers.ItemManager.items
import com.kukurodev.mykukuroaquarium.model.alert.GameAlertType
import java.time.LocalDate
import java.util.Calendar

object Utils {
    var appContext: Context? = null

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun emptyString(): String {
        return ""
    }

    fun hasItem(id: Int): Boolean {
        return items.any { it.id == id }
    }

    fun showToast(message: String) {
        GameAlertManager.show(message)
    }

    fun showToast(
        @StringRes resId: Int,
        vararg formatArgs: Any?,
        type: GameAlertType = GameAlertType.WARNING,
    ) {
        appContext?.let { context ->
            val configuration = Configuration(
                context.resources.configuration
            ).apply {
                setLocale(locale)
            }

            val localizedContext = context.createConfigurationContext(configuration)
            val message = localizedContext.getString(resId, *formatArgs)
            GameAlertManager.show(message, type = type)
        }
    }

    fun ClosedFloatingPointRange<Float>.random(): Float {
        return (start + Math.random() * (endInclusive - start)).toFloat()
    }

    fun Any.toJson(): String {
        return Gson().toJson(this)
    }

    inline fun <reified T> String.fromJson(): T {
        return Gson().fromJson(this, T::class.java)
    }

    fun tomorrowAtMidnight(): Long {
        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

    fun currentDay(): Int {
        return LocalDate.now().toEpochDay().toInt()
    }

    fun currentTime(): Long {
        return LocalDate.now().toEpochDay()
    }

    fun isExpired(time: Long): Boolean {
        return System.currentTimeMillis() >= time
    }

    fun formatRemainingTime(time: Long): String {
        val totalSeconds = time / 1000

        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return String.format(
            "%02d:%02d:%02d",
            hours,
            minutes,
            seconds
        )
    }

    @Composable
    fun Float.toDp(): Dp =
        with(LocalDensity.current) {
            this@toDp.toDp()
        }
}