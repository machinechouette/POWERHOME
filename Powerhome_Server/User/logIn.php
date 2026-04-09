<?php
require_once '../DataBase/db.php';

$email = isset($_POST['email']) ? $_POST['email'] : "";
$password = hash('sha256', $_POST['password']);
$user = userExists($email, $password);

if ($user) {
    echo json_encode(['success' => 'Login successful']);
} else {
    echo json_encode(['error' => 'Identifiants invalides']);
}

?>