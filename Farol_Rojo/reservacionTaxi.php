<?php
$conn = mysql_connect("localhost","root","");
mysql_select_db("con_info",$conn) or die ("Erro en la conección".mysql_error());

$us = $_POST['us'];
$origen = $_POST['origen'];
$destino = $_POST['destino'];
$fecha = $_POST['fecha'];
$hora = $_POST['hora'];
$observacion = $_POST['des'];
$query;
$result;
$contador = 0;
$dato;


function ins(){
$us = $_POST['us'];
$origen = $_POST['origen'];
$destino = $_POST['destino'];
$fecha = $_POST['fecha'];
$hora = $_POST['hora'];
$observacion = $_POST['des'];
	$qury = "INSERT INTO taxi_uber (id_tax , for_cliente_d, origen, destino, fecha, hora,observaciones)
				VALUES ('',".busqueda($us).",'".$origen."','".$destino."','".$fecha."','".$hora."','".$observacion."')";
				$s = mysql_query($qury);
				return $s;

	}
function busqueda($us){
	$query = "SELECT fk_id_cliente FROM user_app WHERE usuario = '".$us."'";
		$result = mysql_query($query);
		
		while ($row=mysql_fetch_object($result)) {
				$fka = $row->fk_id_cliente;
				return $fka;
		}
	}

echo json_encode("<h1>".(busqueda($us))."</h1>");




echo json_encode(ins());

?>