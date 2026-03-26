# 📱 SMS Alert - Quick Messaging App

## 📝 Overview
SMS Alert is an Android application built to demonstrate how to interact with the device's native telephony hardware. It provides a simple, clean GUI that allows a user to input a phone number and a custom message, and then uses the device's cellular network to send a real SMS text message.

This project was built to explore Android permissions, background telephony services, and modern UI input handling.

## ✨ Key Features
* **Native SMS Integration:** Uses Android's native `SmsManager` API to send text messages directly from the application without opening the default messaging app.
* **Runtime Permissions:** Implements secure, modern permission requests to dynamically ask the user for `SEND_SMS` access.
* **Input Validation:** Ensures both the phone number and message fields are populated before attempting to send.
* **Interactive UI:** Built entirely with Jetpack Compose, featuring responsive text fields optimized for different keyboard types (e.g., number pad for the phone number).

## 🛠️ Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Telephony API:** `android.telephony.SmsManager`
* **IDE:** Android Studio

## 🔐 Required Permissions
This application requires the following permission in the `AndroidManifest.xml` to function:
* `SEND_SMS` (Required to send texts through the carrier network)

## 🚀 Getting Started

### Installation & Running the App
1. Open **Android Studio** and load this project folder.
2. Allow the Gradle sync to download any required dependencies.
3. **⚠️ Version Sync (If required):**
   * If you see AAR metadata errors, open `Gradle Scripts` > `libs.versions.toml`.
   * Ensure core libraries are set to stable versions (e.g., `coreKtx = "1.13.1"`, `activity = "1.9.3"`).
   * Click **Sync Now**.
4. Connect a physical Android device equipped with an active SIM card. *(Note: Emulators can simulate SMS, but a physical device is best for testing real cellular transmission).*
5. Click the **Run (▶)** button.

## 📱 Usage
1. Open the app on your device.
2. Enter a valid phone number in the top field.
3. Type your desired text message in the bottom field.
4. Tap **"SEND SMS"**.
5. The first time you use it, Android will prompt you to grant SMS permissions. Tap **Allow**.
6. Tap **"SEND SMS"** again, and a toast notification will confirm that the message was sent successfully!

---
*Developed for Wireless & Mobile App Development Lab*