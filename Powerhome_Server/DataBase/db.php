<?php
$pdo = new PDO('mysql:host=localhost;dbname=powerhome', 'root', '');
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

function getUser($email, $password) {
    global $pdo;
    $stmt = $pdo->prepare("SELECT pseudo FROM Users WHERE email = ? AND password_hash = ?");
    $stmt->execute([$email, $password]);
    return userExists($email, $password);
}

function setUser($email, $pseudo, $password) {
    global $pdo;
    $stmt = $pdo->prepare("INSERT INTO Users (email, pseudo, password_hash) VALUES (?, ?, ?)");
    return $stmt->execute([$email, $pseudo, $password]);
}

function userExists($email, $password) {
    global $pdo;
    $stmt = $pdo->prepare("SELECT * FROM Users WHERE email = ? AND password_hash = ?");
    $stmt->execute([$email, $password]);
    return $stmt->rowCount() == 1;
}

?>