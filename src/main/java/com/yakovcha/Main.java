package com.yakovcha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        String originalPath = args[0];
        String diffPath = args[1];

        //optional
        String originalButtonId;
        if (args.length == 3) {
            originalButtonId = args[2];
        } else {
            originalButtonId = DEFAULT_ID;
        }

        String originalHtml = String.join("", Files.readAllLines(Paths.get(originalPath)));
        String diffHtml = String.join("", Files.readAllLines(Paths.get(diffPath)));


        //TODO don't mind if there are no elements found for now. Just select first.
        Element originalBtn = Jsoup.parse(originalHtml).select("#" + originalButtonId).iterator().next();


        Elements diffBtns = Jsoup.parse(diffHtml).select(".btn");

        List<Integer> score = new ArrayList<>(diffBtns.size());
        
        System.out.println("/html/body/a");
    }
}
