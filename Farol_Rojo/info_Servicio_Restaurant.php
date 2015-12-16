<?php
$conn = mysql_connect("localhost","root","");
mysql_select_db("con_info",$conn) or die ("Erro en la conección".mysql_error());

function busqueda(){
	$us = $_POST['us'];
if($us !=null && $us != ""){
	$query = "SELECT fk_id_cliente FROM user_app WHERE usuario = '".$us."'";
		$result = mysql_query($query);
		
		while ($row=mysql_fetch_object($result)) {
				$fka = $row->fk_id_cliente;
				return $fka;
		}
	}else{
		$fka = "El usuario No existe";
		return $fka;
	}
}
 function informacion()
{
   $query = "SELECT  observaciones, hora, fecha from rest_reservacion,user_app where for_id_cliente = ".busqueda()."";
		$result = mysql_query($query);
		$results = array();
		while($row = mysql_fetch_array($result))
		{
		   $results[] = array(
		      'observaciones' => $row['observaciones'],
		      'hora' => $row['hora'],
		      'fecha' => $row['fecha']
		   );
		}
		return($results);

		
}



echo json_encode(informacion());



?>