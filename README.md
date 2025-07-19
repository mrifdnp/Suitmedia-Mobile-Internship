# IsPalindrome App

IsPalindrome adalah aplikasi Android Native sederhana yang dibangun menggunakan Kotlin dan Jetpack Compose. Aplikasi ini memiliki 3 layar:
1. Mengecek apakah sebuah kalimat adalah palindrom.
2. Menampilkan nama dan user yang dipilih.
3. Menampilkan daftar user dari API [Reqres.in](https://reqres.in/) dengan fitur pagination, pull-to-refresh, dan pilih user.

---

## 🧱 Tech Stack

- Kotlin
- Jetpack Compose
- MVVM Architecture
- Retrofit (REST API)
- StateFlow
- Coroutine
- Pagination & Pull-to-Refresh

---

## 📱 Screenshots
<img width="3460" height="1766" alt="Group 1" src="https://github.com/user-attachments/assets/4ea924b3-a6d9-411e-9c48-26b82949f651" />



---

## 🔧 Fitur

- Cek kalimat palindrom
- Navigasi antar layar
- Ambil data user dari API
- Pagination + Load More
- Pilih user dan tampilkan nama di layar kedua
- State management dengan SharedViewModel

---

## 🚀 Cara Menjalankan Project

1. Clone repositori ini:

   ```bash
   git clone https://github.com/username/isPalindromeApp.git
2. Dapatkan API key dari [https://reqres.in/signup](https://reqres.in/signup).  
   Letakkan API key ke dalam file `local.properties` seperti berikut:
   ```properties
   REQRES_API_KEY="your_api_key_here"

