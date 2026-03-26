# 🔐 GSM Security (A3/A5/A8) Simulator

## 📝 Overview
This Android application simulates the cryptographic algorithms used in standard GSM mobile networks. It visually demonstrates how a mobile device and a cell tower securely authenticate and encrypt voice/text data without ever transmitting the user's actual Secret Key over the air.

## ✨ Key Features
* **A3 Algorithm (Authentication):** Simulates generating a 32-bit Signed Response (SRES) using the Secret Key (Ki) and a Random Challenge (RAND).
* **A8 Algorithm (Key Generation):** Simulates generating a 64-bit Session Key (Kc) for the current connection.
* **A5 Algorithm (Encryption):** Simulates a stream cipher that uses the Session Key (Kc) to XOR encrypt a plaintext message into hexadecimal ciphertext.
* **Educational UI:** Built with Jetpack Compose to clearly display the step-by-step cryptographic flow.

## 🛠️ Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Cryptography:** SHA Hashing & XOR Stream Ciphers (Simulation)
* **IDE:** Android Studio

## 🚀 Usage
1. Launch the app on your Android device or emulator.
2. The fields for **Secret Key (Ki)**, **Random Challenge (RAND)**, and the **Message** are pre-filled for easy testing, but can be customized.
3. Tap **"RUN GSM ALGORITHMS"**.
4. The UI will instantly calculate and display the Authentication SRES, the temporary Session Key, and the final Encrypted Data.

---
*Developed for Wireless & Mobile App Development Lab*