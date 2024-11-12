package com.example.android6

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class BatteryService : Service() {

    private lateinit var batteryReceiver: BatteryReceiver

    override fun onCreate() {
        super.onCreate()
        batteryReceiver = BatteryReceiver()
        // Регистрируем ресивер для отслеживания изменений уровня батареи
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отменяем регистрацию ресивера
        unregisterReceiver(batteryReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}