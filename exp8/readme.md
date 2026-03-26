# 📡 CDMA Multiplexer & Demultiplexer

## 📝 Overview
This application simulates the core concept of Code Division Multiple Access (CDMA) used in wireless telecommunications. It demonstrates how multiple stations can transmit data simultaneously over a single shared channel without interference by using mathematically orthogonal codes.

## ✨ Key Features
* **Orthogonal Coding:** Assigns unique, orthogonal spreading codes (e.g., [1, 1] and [1, -1]) to different simulated stations.
* **Signal Multiplexing:** Mathematically combines the data from Station 1 and Station 2 into a single shared transmission signal.
* **Signal Demultiplexing:** Proves the CDMA concept by extracting the exact original data for each station out of the combined signal.
* **Interactive UI:** Built with Jetpack Compose, allowing users to input test data (1 or -1) and instantly see the mathematical results.

## 🛠️ Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Logic:** Array/List mathematics
* **IDE:** Android Studio

## 🚀 Usage
1. Open the app on your Android device or emulator.
2. Enter `1` or `-1` into the data field for Station 1.
3. Enter `1` or `-1` into the data field for Station 2.
4. Tap **"MULTIPLEX & DECODE"**.
5. The app will display the combined channel signal, and then successfully extract the original 1s and -1s for each station.

---
*Developed for Wireless & Mobile App Development Lab*