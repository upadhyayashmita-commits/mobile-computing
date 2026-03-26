# 🌍 GeoLocator - GPS & Landmark Finder

## 📝 Overview
GeoLocator is an Android application designed to fetch a user's current geographical coordinates (Latitude and Longitude) and convert them into a real-world street address or nearest landmark. 

This project was built to demonstrate the integration of device hardware sensors (GPS) and network-based location services within a modern Android UI.

## ✨ Key Features
* **Precise GPS Tracking:** Utilizes the `FusedLocationProviderClient` to accurately pinpoint the device's current latitude and longitude.
* **Reverse Geocoding:** Uses Android's built-in `Geocoder` to translate raw GPS coordinates into human-readable street addresses and landmarks.
* **Runtime Permissions:** Implements modern Android permission requests for both Location (Fine/Coarse) and Internet access.
* **Responsive UI:** Built with Jetpack Compose, featuring a clean, scrollable interface that adapts to different screen sizes.

## 🛠️ Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Location Services:** Google Play Services Location API
* **Geocoding:** `android.location.Geocoder`
* **IDE:** Android Studio

## 🔐 Required Permissions
This application requires the following permissions in the `AndroidManifest.xml` to function:
* `ACCESS_FINE_LOCATION`
* `ACCESS_COARSE_LOCATION`
* `INTERNET` (Required for the Geocoder to look up landmarks)

## 🚀 Getting Started

### Installation & Running the App
1. Open **Android Studio** and load this project folder.
2. Allow Gradle to sync and download dependencies.
3. **⚠️ Version Sync (If required):**
   * If you see metadata errors, open `Gradle Scripts` > `libs.versions.toml`.
   * Ensure stable versions are set (e.g., `coreKtx = "1.13.1"`, `activity = "1.9.3"`).
   * Click **Sync Now**.
4. Connect a physical Android device or start an Emulator. *(Note: Ensure Location/GPS is turned ON in the device settings).*
5. Click the **Run (▶)** button.

## 📱 Usage
1. Open the app and tap the **"Fetch the location"** button.
2. If prompted, grant the app permission to access your device's location.
3. Tap the button again. The app will display your exact Latitude, Longitude, and the nearest physical street address/landmark.

---
*Developed for Wireless & Mobile App Development Lab*