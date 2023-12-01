<?php

// quando il browser sottomette form per upload di un file, il superglobal
// $_FILES viene popolato con info sul file da caricare

print('<pre>$_FILES = ' . print_r($_FILES, true) . "</pre>\n");
print('<pre>$_POST = ' . print_r($_POST, true) . "</pre>\n");

echo "Normalmente non si farebbe il dump dei superglobal, ";
echo "qui sopra lo si fa a scopo illustrativo<hr>\n";

$target_dir = "_uploads/";
$basename = $_FILES["fileDaCaricare"]["name"];
$src_ext = $_FILES["fileDaCaricare"]["type"];

$target_file = $target_dir . $basename;

echo "costruiamo il pathname <code>\$target_file</code> che il file prender&agrave; sul server: <BR>";
echo "(mantiene il nome <code>$basename</code> che ha sul client e andr&agrave; ";
echo "nella directory <code>$target_dir</code> del server)<br><br>";

echo "<code>\$target_file</code>: <code>$target_file</code><BR>";
$file_ext = strtolower(pathinfo($basename, PATHINFO_EXTENSION));
echo "file extension (estratta da <code>\$target_file</code>): <code>$file_ext</code><BR>";

// Find the file's temporary location
$temp_loc = $_FILES["fileDaCaricare"]["tmp_name"];
echo "Posizione temporanea del file ricevuto: <code>$temp_loc</code><BR><BR>";

echo "file type dichiarato dal cliente (solo dedotto dall'estensione): <code>$src_ext</code><BR>";

// Determine *real* file type

$finfo = finfo_open(FILEINFO_MIME_TYPE);
$file_type = finfo_file($finfo, $temp_loc);
echo "file type <b>vero</b> (da magic number del file temporaneo ricevuto): <code>$file_type</code>";

if ($file_type != "text/plain") {
    echo "Sorry, not a text/plain file.<BR>";
}
// Check file size (dichiarata da cliente)
elseif ($_FILES["fileDaCaricare"]["size"]  // fileDaCaricare e` il "name" scelto
    > 50000) {                            // nel file chooser del cliente
    echo "Sorry, your file is too large (" . $_FILES["fileDaCaricare"]["size"] . " bytes) <BR>";
// tutto ok, ora si sposta veramente il file
} else {
    if (move_uploaded_file($temp_loc, $target_file)) {
        echo "<h3>File " . $temp_loc . " uploaded to $target_file</h3><BR>";
    } else {
        echo "Sorry, there was an error moving your uploaded file from temporary location.<BR>";
    }
}
