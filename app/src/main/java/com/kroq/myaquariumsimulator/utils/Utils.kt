package com.kroq.myaquariumsimulator.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

object Utils {
    var appContext: Context? = null

    fun init(context: Context) {
        appContext = context.applicationContext
    }
    fun showToast(message: String) {
        appContext?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        } ?: Log.e("ToastUtil", "Context henüz init edilmemiş!")
    }

    fun ClosedFloatingPointRange<Float>.random(): Float {
        return (start + Math.random() * (endInclusive - start)).toFloat()
    }
}