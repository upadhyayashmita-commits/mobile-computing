package com.example.memorybox

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class MyRecord(val name: String, val imageBytes: ByteArray?)

class LabDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "LabDB", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image BLOB)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun insertRecord(name: String, imageBytes: ByteArray?) {
        val values = ContentValues().apply {
            put("name", name)
            put("image", imageBytes)
        }
        writableDatabase.insert("users", null, values)
    }

    fun getAllRecords(): List<MyRecord> {
        val list = mutableListOf<MyRecord>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM users", null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(1)
                val image = cursor.getBlob(2)
                list.add(MyRecord(name, image))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbHelper = LabDatabaseHelper(this)
        setContent {
            DatabaseAppUI(dbHelper)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseAppUI(dbHelper: LabDatabaseHelper) {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var records by remember { mutableStateOf(dbHelper.getAllRecords()) }
    var showImageDialog by remember { mutableStateOf<ByteArray?>(null) }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImageUri = uri
    }

    val primaryColor = Color(0xFF1976D2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text("SQLite Image DB", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = primaryColor)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter a name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { imagePicker.launch("image/*") },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(if (selectedImageUri == null) "PICK IMAGE" else "IMAGE SELECTED", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (inputText.isNotBlank() && selectedImageUri != null) {
                    try {
                        val inputStream = context.contentResolver.openInputStream(selectedImageUri!!)
                        val bytes = inputStream?.readBytes()

                        dbHelper.insertRecord(inputText, bytes)
                        records = dbHelper.getAllRecords()

                        inputText = ""
                        selectedImageUri = null
                        Toast.makeText(context, "Saved to DB!", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Enter name and pick image", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("SAVE TO DATABASE", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(30.dp))
        HorizontalDivider(thickness = 2.dp, color = Color.LightGray)
        Spacer(modifier = Modifier.height(20.dp))

        Text("Tap a record to view image:", fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.align(Alignment.Start))

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(records) { record ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { showImageDialog = record.imageBytes },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Text(
                        text = record.name,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 18.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    if (showImageDialog != null) {
        AlertDialog(
            onDismissRequest = { showImageDialog = null },
            confirmButton = {
                Button(onClick = { showImageDialog = null }) {
                    Text("Close")
                }
            },
            text = {
                val bitmap = showImageDialog?.let { BitmapFactory.decodeByteArray(it, 0, it.size) }
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Saved Image",
                        modifier = Modifier.fillMaxWidth().height(300.dp)
                    )
                } else {
                    Text("No Image Available")
                }
            }
        )
    }
}