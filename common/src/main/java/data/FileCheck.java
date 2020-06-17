package data;

import java.io.File;

public class FileCheck {
    public static String checkFile(String fileName) {
        File file = new File(fileName);
        if (file.isDirectory()) {
            return "Необходим обязательный аргумент: Полное имя файла данных, не директория";
        }
        if (!file.exists()) {
            return "Файл не найден";
        }
        if (!file.canRead() && !file.canWrite()) {
            return "Ошибка доступа на чтение и на запись";
        }
        if (!file.canRead()) {
            return "Ошибка доступа на чтение";
        }
        if (!file.canWrite()) {
            return "Ошибка доступа на запись";
        }
        return "0";
    }
}
