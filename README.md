<img width="452" height="428" alt="image" src="https://github.com/user-attachments/assets/6bd5c9c8-b4e4-40ea-b728-1a032b1dd307" # Sistem Pendakian Terpadu

Program ini merupakan sistem manajemen pendaftaran pendakian gunung yang dikembangkan sebagai bagian dari proyek akhir (Capstone Project). Sistem ini dirancang untuk mengelola data rombongan pendaki, alokasi kuota jalur, serta integrasi sistem pembayaran secara sederhana.

## Fitur Utama
* **Pendaftaran Rombongan:** Ketua rombongan dapat memilih jalur pendakian, menambah anggota, dan melakukan simulasi pembayaran.
* **Autentikasi Petugas:** Sistem memiliki akses terbatas bagi petugas untuk mengelola data pendakian melalui login.
* **Manajemen Kuota Otomatis:** Kuota jalur akan berkurang secara otomatis saat pendaftaran berhasil dan bertambah kembali jika data rombongan dihapus.
* **Manajemen Data Manifes:** Petugas dapat melihat daftar rombongan yang terdata secara real-time dan menghapus data rombongan jika diperlukan.
* **Notifikasi Sistem:** Sistem memberikan notifikasi jika daftar manifes kosong atau jika kuota jalur sudah mencapai batas kritis.

## Teknologi yang Digunakan
* **Bahasa Pemrograman:** Java (OOP - Object Oriented Programming)
* **Konsep:** Abstract Class, Interface, ArrayList, dan Input-Output Handling.

## Cara Menjalankan
1. Pastikan komputer Anda sudah terinstal **Java Development Kit (JDK)**.
2. Unduh atau clone repository ini.
3. Buka terminal atau command prompt di folder proyek.
4. Compile semua file Java dengan perintah:
   `javac *.java`
5. Jalankan program dengan perintah:
   `java MainApp`
