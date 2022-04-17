package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        openZip("D://00_java//All_My_Project//Games//savegames//savegames.zip", "D://00_java//All_My_Project//Games//savegames//savegames"  );

        openProgress("D://00_java//All_My_Project//Games//savegames//save1.dat");
        openProgress("D://00_java//All_My_Project//Games//savegames//save2.dat");
        openProgress("D://00_java//All_My_Project//Games//savegames//save3.DAT");
    }

    public static void openZip(String pathZip, String pathUnzip) {
        File unZipFolder = new File(pathUnzip);
        File zipFile = new File(pathZip);

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                // получим название файла
                // распаковка
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read();
                     c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }

    public static GameProgress openProgress(String pathDataFile){
        GameProgress gameProgress = null;
        // откроем входной поток для чтения файла
        try (FileInputStream  fis = new FileInputStream(pathDataFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
        return gameProgress;
    }
}


//*************
//ZIP
/*public static void zipFiles(String path, String... files) {
    File zipFile = new File(path);
    for (String zipedFileName : files
    ) {
        File zippedFile = new File(zipedFileName);
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile));
             FileInputStream fisSave = new FileInputStream(zippedFile);
        ) {
            ZipEntry entry = new ZipEntry("save.txt");
            zout.putNextEntry(entry);

            byte[] buffer = new byte[fisSave.available()];
            fisSave.read(buffer);
            zout.write(buffer);
            zout.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (zippedFile.delete()) System.out.println("файл удален");
    }
}*/
