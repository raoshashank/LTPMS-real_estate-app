<?php
require 'phpmailer/PHPMailerAutoload.php';
require_once 'DB_Connect.php';
$db = new DB_Connect();
$conn = $db->connect();
$mail = new PHPMailer;
$code  = md5(uniqid(rand()));
class send{
	
	
public function sendemail(){
	
	if(isset($_POST['email'])){
$email =$_POST['email'];
$query = "UPDATE login SET code = '$code'  WHERE email = '$email' AND status = 0";
mysqli_query($conn,$query);
//$mail->SMTPDebug = 3;                               // Enable verbose debug output
$mail->isSMTP();                                      // Set mailer to use SMTP
$mail->Host = 'tls://smtp.gmail.com:587';  // Specify main and backup SMTP servers
$mail->SMTPAuth = true;                               // Enable SMTP authentication
$mail->Username = 'randomemail359@gmail.com';                 // SMTP username
$mail->Password = 'rajnikanth';                           // SMTP password
$mail->SMTPSecure = 'tls';                       	     // Enable TLS encryption, `ssl` also accepted
$mail->Port = 587;                                    // TCP port to connect to

$mail->setFrom('from@example.com', 'Mailer');
$mail->addAddress($email, 'Shashank');     // Add a recipient

$mail->isHTML(true);                                  // Set email format to HTML


$mail->Subject = 'Please verify your account';
$mail->Body    = '

<h3>
Welcome! to app name ! </h3></br>
Please click the link below to Verify your email and Complete the Registration Process ></br>


<a href = "http://localhost/webapp/verify.php?code='.$code.'&email='.$email.'" >CLICK HERE</a> 
</h3>



';
$mail->AltBody = 'sampel email';

if(!$mail->send()) {
    $json['error']="Mailer Error: ";
	echo json_encode($json);
} else {
	$json['success'] = "Verification mail has been sent";
    echo json_encode($json);
}
}
}
}
?>
