<?php
require_once 'DB_Connect.php';
$db = new DB_Connect();
$conn = $db->connect();
if(isset($_POST['email'],$_POST['password'])){
	$email = $_POST['email'];
	$password = md5($_POST['password']);
	if(!empty($email) && !empty($password))
	
	{
		
	$query = "SELECT * FROM user where email = '$email' AND password = '$password'";
	$result = mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0)
	{
		$json['success']="Welcome".$email;
		echo json_encode($json); 
	}
	else{

			$json['error'] = "user credentials incorrect ";
			echo json_encode($json);
	}
	}
	
	else {
	
	$json['error']="You must enter all fields";
	echo json_encode($json);
}	
	
	
}




?>