<?php
require_once '../DataBase/db.php';

$email = $_POST['email'];
$newPassword = hash('sha256', $_POST['newPassword']);
if (updatePassword($email, $newPassword)) {
    echo json_encode(['success' => 'Password updated successfully']);
} else {
    echo json_encode(['error' => 'Failed to update password']);
}

?>