import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Directory {

    private File directory;
    private File[] files;

    public Directory(String directory) {                                        //Konstruktor -> sofern der übergebene File ein Ordner ist
        this.directory = new File(directory);
        if (this.directory.isDirectory()) {
            System.out.println(directory + " ist ein Ordner");
            files = this.directory.listFiles();
        }else
            System.out.println("Das ist kein Ordner");
    }

    public void FileList(){                                                    //Gibt die Unterstruktur des Ordner aus.

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                System.out.println("Directory: " + files[i].getName());
            else
                System.out.println("File: " + files[i].getName());
        }
    }

    public void FilePropertyList() throws IOException {                     //Gibt die Unterstrktur aus + Size, Extension, CreationDate, Last Modified

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory())
                System.out.println("Directory: " + files[i].getName());

            else
                System.out.println("File: " + files[i].getName());

            this.FileSize(files[i]);
            this.FileExtension(files[i]);
            this.FileCreationDate(files[i]);
            this.FileLastModified(files[i]);
            System.out.println("------------------------------");
        }

    }

    public void FileExtension(){                                                 //Liest alle Datentypen im Ordner aus.

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile())
                System.out.println(files[i].getName() + " ----> " + getFileExtension(files[i]));
        }
    }

    public void FileExtension(File file){                                        //Liest den Datentypen des übergebenen File aus.
        if (file.isFile())
            System.out.println(getFileExtension(file));
    }

    public void FileSize(){                                                      //Liest alle Dateigrößen im Ordner aus.

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                System.out.println(files[i].getName() + " Size: " + files[i].length() + " KB");
            }
        }
    }

    public void FileSize(File file){                                            //Liest die Dateigröße des übergebenen File aus.

        if (file.isFile())
            System.out.println(file.length() + " KB");
    }

    public void FileLastModified(){                                             //Liest alle zuletzt-geändert Daten im Ordner aus.

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String lm = sdf.format(files[i].lastModified());
                System.out.println(files[i].getName() + " Last Modified: " + lm);
            }
        }
    }

    public void FileLastModified(File file){                                    //Liest das zuletzt-geändert Datum des übergebenen File aus.
        if (file.isFile()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lm = sdf.format(file.lastModified());
            System.out.println(lm);
        }
    }

    public void FileCreationDate() throws IOException {                         //Liest alle Erstellungsdaten im Ordner aus.

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile())
                System.out.println(files[i].getName() + " Creation Date " + getFileCreationDate(files[i]));
        }
    }

    public void FileCreationDate(File file) throws IOException {               //Liest das Erstellungsdatum des übergebenen File aus.

        if (file.isFile())
            System.out.println( getFileCreationDate(file));
    }

    private static String getFileExtension(File file) {                        //Ermittelt den Datentyp des übergebenen File.
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private static String getFileCreationDate(File file) throws IOException {   //Ermittelt das Erstellungsdatum des übergebenen File.

        Path path = file.toPath();
        BasicFileAttributes fatr = Files.readAttributes(path, BasicFileAttributes.class);
        FileTime creationTime = fatr.creationTime();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String lm = df.format(creationTime.toMillis());
        return lm;
    }


    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

}
