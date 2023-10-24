<?php
function myTest() {
  static $x = 0; #visibile al di fuori dello scope
  echo "$x\r\n"; #tabulare 
  echo "\n<br>\n"; #andare a capo nel file 
  $x++;
}

myTest();
myTest();
myTest();
?>