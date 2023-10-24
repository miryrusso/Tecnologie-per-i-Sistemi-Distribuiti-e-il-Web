<!DOCTYPE html>
<html>
<body>

<?php
$x = 5;
$y = 10;

function myTest() {
  global $x, $y; #se non ci fosse questa dichiarazione di variabile non prenderebbe i valori e y sarebbe 0
  $y = $x + $y;
} 

myTest();  // run function
echo $y; // output the new value for variable $y
?>

</body>
</html>