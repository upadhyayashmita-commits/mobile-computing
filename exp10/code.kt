package com.example.guicomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GUIAppUI()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GUIAppUI() {
    var name by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Not Selected") }
    var isEnrolled by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf("Fill out the form to see results") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8BBD0))
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text("GUI Components Form", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF880E4F))

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Student Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Select Gender:", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            RadioButton(selected = selectedGender == "Male", onClick = { selectedGender = "Male" })
            Text("Male")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = selectedGender == "Female", onClick = { selectedGender = "Female" })
            Text("Female")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Checkbox(
                checked = isEnrolled,
                onCheckedChange = { isEnrolled = it }
            )
            Text("Enroll in Wireless Telecom Lab", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                val status = if (isEnrolled) "Enrolled" else "Not Enrolled"
                resultText = "Name: $name\nGender: $selectedGender\nStatus: $status"
            },
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF880E4F)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("SUBMIT FORM", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 120.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = resultText,
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}