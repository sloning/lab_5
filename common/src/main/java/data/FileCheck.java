package data;

import java.io.File;

public class FileCheck {
    public static int checkFile(String fileName) {
        File file = new File(fileName);
        if (file.isDirectory()) {
            System.out.println("Необходим обязательный аргумент: Полное имя файла данных, не директория");
            return 1;
        }
        if (!file.exists()) {
            System.out.println("Файл не найден");
            return 1;
        }
        if (!file.canRead() && !file.canWrite()) {
            System.out.println("Ошибка доступа на чтение и на запись");
            return 1;
        }
        if (!file.canRead()) {
            System.out.println("Ошибка доступа на чтение");
            return 1;
        }
        if (!file.canWrite()) {
            System.out.println("Ошибка доступа на запись");
            return 1;
        }
        return 0;
    }
}
