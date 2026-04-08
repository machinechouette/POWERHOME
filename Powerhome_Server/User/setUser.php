<?php
require_once '../DataBase/db.php';

$email = $_GET['email'];
$pseudo = $_GET['pseudo'];
$password = hash('sha256', $_GET['password']);

if (setUser($email, $pseudo, $password)) {
    echo json_encode(['success' => 'User created successfully']);
} else {
    echo json_encode(['error' => 'Failed to create user']);
}

?>