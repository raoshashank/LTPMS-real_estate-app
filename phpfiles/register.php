<?php
require_once 'DB_Connect.php';
require 'phpmailer/PHPMailerAutoload.php';
header('Content-Type : application/json');
$db = new DB_Connect();	
$conn = $db->connect();
$mail = new PHPMailer;
$code  = md5(uniqid(rand()));


if(isset($_POST['email'],$_POST['password']))
{
	
	$email = $_POST['email'];
	$password = md5($_POST['password']);
	if(!empty($email) && !empty($password) )
	
	{
			if(filter_var($email,FILTER_VALIDATE_EMAIL)!=5)				//Change during submission
			{
				
				$query = "SELECT * FROM user where email = '$email' AND password = '$password'";
				$result = mysqli_query($conn,$query);
				if(mysqli_num_rows($result)>0)
				{
						$json['error']="User already exists with email".$email;
						echo json_encode($json); 
				}
			
				 else{
						
						$code = md5(uniqid(rand()));
						$query = "INSERT INTO user(user_id,email,password,code) values ('$user_id','$email','$password','$code')";
						$result =mysqli_query($conn,$query);
						if($result == 1 )
						{
							
						
								$query = "UPDATE user SET code = '$code'  WHERE email = '$email' AND email_verif_stat = 0";
								mysqli_query($conn,$query);
		
								$mail->isSMTP();                                      // Set mailer to use SMTP
								$mail->Host = 'tls://smtp.gmail.com:587';  // Specify main and backup SMTP servers
								$mail->SMTPAuth = true;                               // Enable SMTP authentication
								$mail->Username = 'randomemail359@gmail.com';                 // SMTP username
								$mail->Password = 'rajnikanth';                           // SMTP password	
								$mail->setFrom('from@example.com', 'LTPMS');
								$mail->addAddress($email, 'Shashank');     // Add a recipient
								$mail->isHTML(true);                                  // Set email format to HTML
								$mail->Subject = 'Please verify your account';
								$mail->Body    = '
	
									<h3>
									Welcome! to LTPMS app ! </h3></br>
									Please click the link below to Verify your email and Complete the Registration Process ></br>


									<a href = "http://localhost/webapp/verify.php?code='.$code.'&email='.$email.'" >CLICK HERE</a> 
									</h3>';
								$mail->AltBody = 'sampel email';
								$mail->send();

								$json ['success'] = "Registration Success! An Email Has Been Sent to you. Check it out!!";
								echo json_encode($json);
							
						}
					
						else {
									$json['error']="You already have an account!!";
									echo json_encode($json);
							 }
					 }
	
	
			}else{
						$json['error']="Please Enter a valid Email account";
						echo json_encode($json);
		
				 }
	}	
		
		
	else 
	{
			    $json['error']="You must enter all fields";
				echo json_encode($json);
	}	
	
}







?>
