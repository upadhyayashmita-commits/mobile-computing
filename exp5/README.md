# 🗄️ Android Local Database App

## 📝 Overview
This Android application demonstrates how to implement local data storage using a database (SQLite / Room). It features a clean Graphical User Interface (GUI) built with Jetpack Compose that allows users to seamlessly interact with the underlying database to manage records.

This project was built as part of an Android Mobile Application Development lab experiment.

## ✨ Key Features
* **Create:** Insert new records (e.g., student details, user profiles) into the database.
* **Read:** Fetch and display existing records on the screen dynamically.
* **Update:** Modify existing data entries.
* **Delete:** Remove specific records from the database.
* **Modern UI:** Built entirely using declarative UI (Jetpack Compose).

## 🛠️ Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Database:** SQLite / Room Persistence Library
* **IDE:** Android Studio

## 🚀 Getting Started

### Prerequisites
* **Android Studio:** Jellyfish (or newer)
* **Minimum SDK:** API 24 (Android 7.0)
* **Target SDK:** API 34+

### Installation & Running the App
1. Open **Android Studio**.
2. Select **File > Open** and select this project folder.
3. Wait for the Gradle sync to finish downloading dependencies.
4. **⚠️ Important Version Fix (If you encounter AAR metadata errors):**
   * Open `Gradle Scripts` > `libs.versions.toml`.
   * Ensure your core libraries are set to stable versions (e.g., `coreKtx = "1.13.1"`, `activity = "1.9.3"`).
   * Click **Sync Now**.
5. Connect your Android device via USB (with USB Debugging enabled) or start an Android Emulator.
6. Click the green **Run (▶)** button in the top toolbar.

## 📱 Usage
1. Launch the app on your device.
2. Use the input fields to enter new data and tap the **Save/Insert** button.
3. Scroll through the list to view the saved database entries.
4. Use the provided UI buttons to edit or delete specific records.

---
*Developed for Wireless & Mobile App Development Lab*