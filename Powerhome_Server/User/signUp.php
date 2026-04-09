<?php
require_once '../DataBase/db.php';

$email = $_POST['email'];
$pseudo = $_POST['pseudo'];
$password = hash('sha256', $_POST['password']);

if (setUser($email, $pseudo, $password)) {
    echo json_encode(['success' => 'User created successfully']);
} else {
    echo json_encode(['error' => 'Failed to create user']);
}

?>