<!DOCTYPE html>
<html>
<body>

<?php
$x = 5;
$y = 10;

function myTest() {
  $GLOBALS['y'] = $GLOBALS['x'] + $GLOBALS['y']; #questo ci permette di non dichiarare le variabili global all'interno della funzione
} 

myTest();
echo $y;
?>

</body>
</html>
