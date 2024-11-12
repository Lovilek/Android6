package com.example.android6
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android6.ui.theme.Android6Theme

class MainActivity : ComponentActivity() {

    companion object {
        var batteryLevel = mutableStateOf(-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Запускаем сервис для отслеживания уровня батареи
        val serviceIntent = Intent(this, BatteryService::class.java)
        startService(serviceIntent)

        setContent {
            Android6Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding) // Используем innerPadding для отступов
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Отображаем уровень заряда батареи
                            BatteryLevelDisplay(batteryLevel.value)
                        }
                    }
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Остановить сервис при завершении Activity
        stopService(Intent(this, BatteryService::class.java))
    }
}

@Composable
fun BatteryLevelDisplay(level: Int) {
    Text(
        text = if (level >= 0) "Уровень заряда батареи: $level%" else "Получение данных...",
        modifier = Modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BatteryLevelDisplayPreview() {
    Android6Theme {
        BatteryLevelDisplay(75)
    }
}
