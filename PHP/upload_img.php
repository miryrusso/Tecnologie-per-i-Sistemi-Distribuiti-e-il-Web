<?php

// quando il browser sottomette form per upload di un file, il superglobal
// $_FILES viene popolato con info sul file da caricare

print('<pre>$_FILES = ' . print_r($_FILES, true) . "</pre>\n");
print('<pre>$_POST = ' . print_r($_POST, true) . "</pre>\n");

echo "Normalmente non si farebbe il dump dei superglobal, ";
echo "qui sopra lo si fa a scopo illustrativo<hr>\n";

foreach ($_FILES as $file_key => $file_descr) {
    break;
}

echo "Chiave per primo file in upload: <code>\"$file_key\"</code><hr>";

include "upload_err_handle.ppp";

if (($errno = $file_descr["error"]) > 0)
    exit("<h3>$upload_err_msg[$errno]</h3>");

$target_dir = "_uploads/";
$basename = $file_descr["name"];
$src_ext = $file_descr["type"];

$target_file = $target_dir . $basename;

echo "costruiamo il pathname <code>\$target_file</code> che il file prender&agrave; sul server: ";
echo "(mantiene il <BR>nome <code>$basename</code> che ha sul client e andr&agrave; ";
echo "nella directory <code>$target_dir</code> del server)<br><br>";

echo "<code>\$target_file</code>: <code>$target_file</code><BR>";
$file_ext = strtolower(pathinfo($basename, PATHINFO_EXTENSION));
echo "file extension (estratta da <code>\$target_file</code>): <code>$file_ext</code><BR>";

// Find the file's temporary location
$temp_loc = $file_descr["tmp_name"];
echo "Posizione temporanea del file ricevuto: <code>$temp_loc</code><BR><BR>";

echo "file type dichiarato dal cliente (di fatto solo dedotto dall'estensione): <code>$src_ext</code><BR>";

// Check if image file is actual image or fake image
if (isset($_POST["invia"]))   // scoraggia POST non provenienti dal form
{
    $check = getimagesize($temp_loc); // verifica insufficiente, meglio Fileinfo vedi
    if ($check) {                     // https://www.php.net/manual/function.getimagesize
        echo "file type determinato da <code>getimagesize($temp_loc)</code>:" .
        "<code>" . $check["mime"] . "</code>" . " (type " . $check[2] . ")<BR>";
        $uploadOk = 1;
    } else {
        echo "<h3>Sorry: file not an image for <code>getimagesize()</code></h3>";
        echo "Output di <code>getimagesize($temp_loc)</code>: ";
        print("<pre>" . print_r($check, true) . "</pre>\n");
        $uploadOk = 0;
    }
}

// Check if file already exists
if (file_exists($target_file)) {
    echo "<h3>Sorry, file already exists</h3>";
    $uploadOk = 0;
}
// Check file size (da $_FILES[]['size'])
if ($file_descr['size'] > MAX_SIZE_FOR_SCRIPT) {
    echo "<h3>Sorry, your file is too large (" . $file_descr["size"];
    echo ", scripts accepts " . MAX_SIZE_FOR_SCRIPT . " max)</h3>";
    $uploadOk = 0;
}
// Controlla estensione (non il tipo del file)
if ($file_ext != "jpg" && $file_ext != "png" && $file_ext != "jpeg"
    && $file_ext != "gif" ) {
    echo "<h3>Sorry, only JPG, JPEG, PNG & GIF extensions are allowed</h3>";
    $uploadOk = 0;
}
// Check if $uploadOk was set to 0 by an error
if ($uploadOk == 0) {
    echo "<h3>Sorry, your file was not uploaded</h3>";
// tutto ok, ora si sposta veramente il file
} else {
    if (move_uploaded_file($temp_loc, $target_file)) {
        echo "<h3>File " . $temp_loc . " uploaded to $target_file</h3>";
    } else {
        echo "<h3>Sorry, error moving uploaded file from temporary location</h3>";
    }
}
