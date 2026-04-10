<?php
require_once '../DataBase/db.php';

$email = isset($_POST['email']) ? $_POST['email'] : "";
$password = hash('sha256', $_POST['password']);
$user = getUser($email, $password);

if ($user) {
    echo json_encode($user);
} else {
    echo json_encode(['error' => 'Identifiants invalides']);
}

?>