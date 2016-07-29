<?php
require_once 'DB_Connect.php';
$db = new DB_Connect();	
$conn = $db->connect();

$email = $_POST['email'];
$name = $_POST['name'];
$cntc1 = $_POST['cntc1'];
$perman = $_POST['perman'];
$query = "SELECT * FROM user where email = '$email' ";
if(mysqli_num_rows(mysqli_query($conn,$query))>0){
	$update_query = "UPDATE user set contact_1='$cntc1',permanent_address='$perman', name = '$name' WHERE email = '$email'";
	mysqli_query($conn,$update_query);
	$json["success"] = "Profile edited successfully";
	echo json_encode($json);
}
else{
	$json['error']= "Error";
	echo json_encode($json);
}

?>