package com.slinger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static SingletonHelper singletonHelper = SingletonHelper.getInstance();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        if (args.length == 1) {
            String text = readFileAsString(args[0]);
            Controller controller = new Controller(text, scanner);
            controller.run();
        } else {
            System.out.println("Invalid arguments passed! Must pass a file!");
            System.out.println("Program will not run!");
        }

    }

    private static String readFileAsString(String fileName) {
        String data = "";

        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            System.out.println("No file found with name " + fileName + "!");
            System.exit(0);
        }

        return data;
    }


}
