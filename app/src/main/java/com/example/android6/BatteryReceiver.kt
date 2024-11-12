package com.example.android6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class BatteryReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val level = intent.getIntExtra("level", -1)
        if (level != -1) {
            // Показать уведомление с уровнем заряда батареи
            Toast.makeText(context, "Уровень заряда батареи: $level%", Toast.LENGTH_SHORT).show()

            // Здесь можно обновить интерфейс, отправив уровень заряда
            MainActivity.batteryLevel.value = level
        }
    }
}
