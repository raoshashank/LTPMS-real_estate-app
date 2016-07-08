<?php
require_once 'DB_Connect.php';
$db = new DB_Connect();
$conn = $db->connect();
$email=$_POST['email'];
$query = "SELECT status FROM login WHERE email='$email'AND status = 1";
$result = mysqli_query($conn,$query);
if(mysqli_num_rows($result)==1)
{
	$json['success']="Email Verified! Welcome !";
	echo json_encode($json);
}
else{
	$json['error']="Email couldnt be Verified! Please Try again !";
	echo json_encode($json);
	
}


?>