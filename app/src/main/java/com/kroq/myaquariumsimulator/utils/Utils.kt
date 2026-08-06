package com.kroq.myaquariumsimulator.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.google.gson.Gson
import com.kroq.myaquariumsimulator.managers.ItemManager.items
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
        appContext?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        } ?: Log.e("ToastUtil", "Context henüz init edilmemiş!")
    }

    fun showToast(
        @StringRes resId: Int,
        vararg formatArgs: Any?
    ) {
        appContext?.let {
            Toast.makeText(
                it,
                it.getString(resId, *formatArgs),
                Toast.LENGTH_SHORT
            ).show()
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

    @Composable
    fun Float.toDp(): Dp =
        with(LocalDensity.current) {
            this@toDp.toDp()
        }
}