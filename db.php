<?php
$host = "localhost";
$user = "root";   // Ganti sesuai user database
$password = "";   // Ganti sesuai password database
$dbname = "prakweb_2024_c_223040136";

// Koneksi ke database
$conn = new mysqli($host, $user, $password, $dbname);

// Periksa koneksi
if ($conn->connect_error) {
    die("Koneksi gagal: " . $conn->connect_error);
}
?>