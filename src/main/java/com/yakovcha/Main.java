package com.yakovcha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

    public static final String DEFAULT_ID = "make-everything-ok-button";

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("please provide two file pathes");
        }
        String firstFilePath = args[0];
        String secondFilePath = args[1];

        //optional
        String origId;
        if (args.length == 3) {
            origId = args[2];
        } else {
            origId = DEFAULT_ID;
        }

        List<String> orig = Files.readAllLines(Paths.get(firstFilePath));
        List<String> toDiff = Files.readAllLines(Paths.get(secondFilePath));


        Document doc = Jsoup.parse(String.join("", orig));
        Elements origBtn = doc.select("#" + origId);
        Element next = origBtn.iterator().next();

        System.out.println("/html/body/a");
    }
}
