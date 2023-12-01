<!DOCTYPE html>
<html>

<?php $max_size = 4000000; ?> <!--Dimensione massima del file che si può caricare in MB -->

<body>
    <!--Cosa deve accadere, inviare il messaggio. Upload text indica a chi inviare il file, enctype indica che 
        possiamo mandare in questo caso un file binario (txt, png) -->
   <form action="upload_text.php" method="post" enctype="multipart/form-data">
        Clic per scegliere il file testo da caricare:
        <input type="file" name="fileDaCaricare"><BR><BR>
        <!-- L'elemento "file" qui sopra è un "file chooser" (explorer del S.O.)
             una volta scelto il file, l'upload si attiva col bottone sotto -->
        <input type="submit" value="Premi per upload">
    </form>
    <BR><BR><HR>  
    <form action="upload_img.php" method="post" enctype="multipart/form-data">
        <input type="hidden" name="MAX_FILE_SIZE" value=" <?= $max_size ?> ">
        Clic per scegliere il file immagine da caricare (max <?= $max_size/1000000 ?> MB):
        <input type="file" name="fileDaCaricare"><BR><BR>
        <!-- L'elemento "file" qui sopra è un "file chooser" (explorer del S.O.)
             una volta scelto il file, l'upload si attiva col bottone sotto -->

        <input type="submit" value="Premi per upload" name="invia">
        <!-- la presenza di name="invia" e value="Premi per upload" fa sì che il
             POST porti un parametro con chiave "invia" e valore "Premi per upload"
             NB: questo è anche il testo visualizzato nel bottone -->
    </form>
</body>

</html>