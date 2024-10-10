package ru.vspochernin;

/**
 * @author pochernin-vla
 */
public class Main {

    // Key file storing secret key from https://yandex.ru/pogoda/b2b/console/api-page.
    private static final String KEY_FILE_NAME = "key.txt";

    public static void main(String[] args) {
        String key = KeyReaderUtils.readKeyFromFile(KEY_FILE_NAME);
        System.out.println(key);
    }
}