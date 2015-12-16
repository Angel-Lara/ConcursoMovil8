<?php
$conn = mysql_connect("localhost","root","");
mysql_select_db("con_info",$conn) or die ("Erro en la conección".mysql_error());

$us = $_POST['us'];
$categoria = $_POST['cat'];
$fecha = $_POST['fecha'];
$hora = $_POST['hora'];
$total = $_POST['total'];
$observacion = $_POST['des'];
$query;
$result;
$contador = 0;
$dato;


function ins(){
	$us = $_POST['us'];
$categoria = $_POST['cat'];
$fecha = $_POST['fecha'];
$hora = $_POST['hora'];
$total = $_POST['total'];
$observacion = $_POST['des'];
	$qury = "INSERT INTO rest_reservacion (id_reser , for_id_cliente, ubicacion_mesa, fecha, hora, total_personas,observaciones)
				VALUES ('',".busqueda($us).",'".$categoria."','".$fecha."','".$hora."','".$total."','".$observacion."')";
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