<?php
$pdo = new PDO('mysql:host=localhost;dbname=powerhome', 'root', '');
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

function getUser($email, $password) {
    global $pdo;
    $stmt = $pdo->prepare("SELECT pseudo FROM Users WHERE email = ? AND password_hash = ?");
    $stmt->execute([$email, $password]);
    return $stmt->fetch(PDO::FETCH_ASSOC);
}

function setUser($email, $pseudo, $password) {
    global $pdo;
    $stmt = $pdo->prepare("INSERT INTO Users (email, pseudo, password_hash) VALUES (?, ?, ?)");
    return $stmt->execute([$email, $pseudo, $password]);
}

function updatePassword($email, $newPassword) {
    global $pdo;
    if (!userExists($email)) {
        return false;
    }
    $stmt = $pdo->prepare("UPDATE Users SET password_hash = ? WHERE email = ?");
    return $stmt->execute([$newPassword, $email]);
    return true;
}

function userExists($email) {
    global $pdo;
    $stmt = $pdo->prepare("SELECT * FROM Users WHERE email = ?");
    $stmt->execute([$email]);
    return $stmt->rowCount() == 1;
}

/*function updateProfile($currentEmail, $newPseudo, $newEmail, $newPasswordHash) {
    global $pdo;
    if ($newPasswordHash !== null) {
        $stmt = $pdo->prepare(
            "UPDATE Users SET pseudo = ?, email = ?, password_hash = ?
             WHERE email = ?"
        );
        return $stmt->execute([$newPseudo, $newEmail, $newPasswordHash, $currentEmail]);
    } else {
        $stmt = $pdo->prepare(
            "UPDATE Users SET pseudo = ?, email = ? WHERE email = ?"
        );
        return $stmt->execute([$newPseudo, $newEmail, $currentEmail]);
    }
}
*/
?>