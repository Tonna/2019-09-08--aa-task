package com.yakovcha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            throw new IllegalArgumentException("please provide two file pathes");
        }
        String firstFilePath = args[0];
        String secondFilePath = args[1];

        List<String> orig = Files.readAllLines(Paths.get(firstFilePath));
        List<String> toDiff = Files.readAllLines(Paths.get(secondFilePath));
    }
}
