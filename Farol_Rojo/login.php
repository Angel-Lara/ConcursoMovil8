<?php
$conn = mysql_connect("localhost","root","");
mysql_select_db("con_info",$conn) or die ("Erro en la conección".mysql_error());
$us = $_POST['us'];
$pass = $_POST['con'];
$query;
$result;
$contador = 0;
 


	



function login($us,$pass){
$query = "SELECT COUNT(*) FROM user_app WHERE usuario = '".$us."' AND contrasena = '".$pass."'";
		$result = mysql_query($query);
		$count = mysql_fetch_row($result);
	
	if ($count[0]==0){

		return true;

		}else{

		return false;

		}

}
echo json_encode(login($us,$pass));

?>