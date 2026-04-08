<?php
require_once '../DataBase/db.php';

$email = $_GET['email'];
$password = hash('sha256', $_GET['password']);
$user = getUser($email, $password);

if ($user) {
    echo json_encode($user);
} else {
    echo json_encode(['error' => 'Identifiants invalides']);
}

?>