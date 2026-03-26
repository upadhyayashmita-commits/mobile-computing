package com.example.cdmagui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CDMAAppUI()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CDMAAppUI() {
    var data1 by remember { mutableStateOf("") }
    var data2 by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("Enter 1 or -1 for each station") }

    val code1 = listOf(1, 1)
    val code2 = listOf(1, -1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("CDMA Simulator", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6A1B9A))

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = data1,
            onValueChange = { data1 = it },
            label = { Text("Station 1 Data (Type 1 or -1)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = data2,
            onValueChange = { data2 = it },
            label = { Text("Station 2 Data (Type 1 or -1)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                try {
                    val d1 = data1.toInt()
                    val d2 = data2.toInt()

                    val combinedSignal = listOf(
                        (d1 * code1[0]) + (d2 * code2[0]),
                        (d1 * code1[1]) + (d2 * code2[1])
                    )

                    val decoded1 = ((combinedSignal[0] * code1[0]) + (combinedSignal[1] * code1[1])) / 2
                    val decoded2 = ((combinedSignal[0] * code2[0]) + (combinedSignal[1] * code2[1])) / 2

                    resultText = "Shared Channel Signal:\n$combinedSignal\n\nExtracted Station 1: $decoded1\nExtracted Station 2: $decoded2"
                } catch (e: Exception) {
                    resultText = "Please enter valid integers (1 or -1)"
                }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("MULTIPLEX & DECODE", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 150.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = resultText,
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}