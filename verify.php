<?php
require_once 'DB_Connect.php';
$db = new DB_Connect();
$conn = $db->connect();
$email = $_GET['email'];
$unique_code = $_GET['code'];
$query = "SELECT email_verif_stat FROM user WHERE email = '$email' AND code = '$unique_code' AND email_verif_stat = 0;";
$result =mysqli_query($conn,$query);

if(mysqli_num_rows($result) == 1){
  
	
	$query = "UPDATE user SET email_verif_stat = '1' WHERE email = '$email' AND code = '$unique_code';";
	mysqli_query($conn,$query);
	//$json['success'] = "Email verification success !! Welcome user";
	echo "email verification success!! Welcome User!!";
}
else{
	$json['error'] = "Email not verified please try again";
	echo "your email has already been verified! Thank You for registering with us!";
}
?>