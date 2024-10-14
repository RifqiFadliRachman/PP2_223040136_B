<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Buku</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS (untuk ikon) -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center text-primary"><i class="fas fa-book"></i> Daftar Buku</h2>
    
    <div class="table-responsive">
        <table class="table table-bordered table-hover table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID <i class="fas fa-id-badge"></i></th>
                    <th>Judul Buku <i class="fas fa-book-open"></i></th>
                    <th>Pengarang <i class="fas fa-user"></i></th>
                    <th>Penerbit <i class="fas fa-building"></i></th>
                    <th>Tahun Terbit <i class="fas fa-calendar-alt"></i></th>
                </tr>
            </thead>
            <tbody>
                <?php
                // Query untuk mengambil data buku
                include 'db.php';
                $sql = "SELECT * FROM buku";
                $result = $conn->query($sql);

                if ($result->num_rows > 0) {
                    // Menampilkan setiap baris data
                    while($row = $result->fetch_assoc()) {
                        echo "<tr>
                                <td>" . $row['id'] . "</td>
                                <td>" . $row['judul'] . "</td>
                                <td>" . $row['pengarang'] . "</td>
                                <td>" . $row['penerbit'] . "</td>
                                <td>" . $row['tahun_terbit'] . "</td>
                            </tr>";
                    }
                } else {
                    echo "<tr><td colspan='5' class='text-center'>Tidak ada data buku</td></tr>";
                }

                $conn->close();
                ?>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Font Awesome JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>
