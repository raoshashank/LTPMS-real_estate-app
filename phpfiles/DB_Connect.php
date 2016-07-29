 <?php
require_once 'Config.php';
class DB_Connect {
		private $conn;

	 public function connect()
	{	
	//connecting to database
	 $this->conn = mysqli_connect(hostname,username,password,db_name) or die ("DB Connection failed");	
	return $this->conn;
	
	
	
	}	
}




?>
