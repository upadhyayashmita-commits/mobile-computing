package com.example.smsalert

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMSAppUI()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SMSAppUI() {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                Toast.makeText(context, "Permission granted! Tap send again.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission denied. Cannot send SMS.", Toast.LENGTH_LONG).show()
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("SMS Alert", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100))

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message") },
            modifier = Modifier.fillMaxWidth().height(120.dp),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                if (phoneNumber.isNotBlank() && message.isNotBlank()) {
                    val hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED

                    if (hasPermission) {
                        try {
                            val smsManager: SmsManager = context.getSystemService(SmsManager::class.java)
                            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                            Toast.makeText(context, "SMS Sent Successfully!", Toast.LENGTH_SHORT).show()
                            phoneNumber = ""
                            message = ""
                        } catch (e: Exception) {
                            Toast.makeText(context, "Failed to send SMS.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        permissionLauncher.launch(Manifest.permission.SEND_SMS)
                    }
                } else {
                    Toast.makeText(context, "Please enter number and message", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE65100)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("SEND SMS", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}