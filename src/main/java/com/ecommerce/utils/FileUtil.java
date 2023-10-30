package com.ecommerce.utils;

import java.io.File;

public class FileUtil {

    private static File credentailsFile;

    public static File getCredentialsFile() {
        if (credentailsFile == null)
            credentailsFile = new File("src/main/java/com/ecommerce/assets/credentials.csv");
        return credentailsFile;
    }

    public static String getFilePath() {
        return "src/main/java/com/ecommerce/assets/";
    }


    public static String getOrderPath() {
        return "src/main/java/com/ecommerce/assets/Cart.txt";
    }
}
