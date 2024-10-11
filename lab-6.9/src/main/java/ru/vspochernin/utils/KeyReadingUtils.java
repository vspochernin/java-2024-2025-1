package ru.vspochernin.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KeyReadingUtils {

    public static String readKeyFromFile(String fileName) {
        Path keyFile = Paths.get(fileName);

        validateFile(keyFile);
        String key = readKey(keyFile);
        validateKey(key, keyFile);

        return key;
    }

    private static void validateFile(Path keyFile) {
        if (!Files.exists(keyFile)) {
            throw new RuntimeException(String.format("Key file %s not found", keyFile));
        }
        if (!Files.isRegularFile(keyFile)) {
            throw new RuntimeException(String.format("Key file %s is not a regular file", keyFile));
        }
        if (!Files.isReadable(keyFile)) {
            throw new RuntimeException(String.format("Key file %s is not readable", keyFile));
        }
    }

    private static String readKey(Path keyFile) {
        String key;
        try (BufferedReader br = new BufferedReader(new FileReader(keyFile.toFile()))) {
            key = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error reading key file: %s", keyFile), e);
        }
        return key;
    }

    private static void validateKey(String key, Path keyFile) {
        if (key == null || key.isEmpty()) {
            throw new RuntimeException(String.format("Key file %s is empty", keyFile));
        }
    }
}
