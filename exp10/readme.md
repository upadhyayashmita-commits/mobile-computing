# 📋 GUI Components - Student Registration Form

## 📝 Overview
This application was developed to demonstrate the implementation and state management of essential Graphical User Interface (GUI) components in modern Android development. It features a complete student registration form that captures different types of user input and processes them into a clean results display.

## ✨ Key Features
* **Text Input:** Utilizes `OutlinedTextField` to capture standard keyboard string data (Student Name).
* **Single-Choice Selection:** Implements a `RadioButton` group to ensure mutually exclusive selections (Gender).
* **Boolean Toggles:** Uses a `Checkbox` to handle true/false boolean states (Lab Enrollment).
* **Action Triggers:** Uses Material Design `Button` components to process the collected state data.
* **Dynamic Display:** Uses a `Card` and `Text` to instantly display the consolidated results back to the user upon submission.

## 🛠️ Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **State Management:** Compose `remember` and `mutableStateOf`
* **IDE:** Android Studio

## 🚀 Usage
1. Launch the app on your Android device or emulator.
2. Type a name into the text field.
3. Select a gender using the provided radio buttons.
4. Check or uncheck the enrollment box.
5. Tap **"SUBMIT FORM"**.
6. The app will immediately read the state of all GUI components and display your formatted results in the card at the bottom of the screen.

---
*Developed for Wireless & Mobile App Development Lab*