<?php
$pdo = new PDO('mysql:host=localhost;dbname=powerhome', 'username', 'password');
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

function getUser($email, $password) {
    global $pdo;
    $stmt = $pdo->prepare("SELECT pseudo FROM Users WHERE email = ? AND password_hash = ?");
    $stmt->execute([$email, $password]);
    return $stmt->fetch(PDO::FETCH_ASSOC);
}

function setUser($email, $pseudo, $password) {
    global $pdo;
    $passwordHash = hash('sha256', $password);
    $stmt = $pdo->prepare("INSERT INTO Users (email, pseudo, password_hash) VALUES (?, ?, ?)");
    return $stmt->execute([$email, $pseudo, $passwordHash]);
}

?>