package com.yakovcha;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Element originalBtn =
                Jsoup.parse(originalHtml).select("#" + originalButtonId).iterator().next();

        Elements diffBtns = Jsoup.parse(diffHtml).select(".btn");

        //cool java 8 substitution for init loop.
        List<Integer> score =
                IntStream.range(0, diffBtns.size()).mapToObj(i -> 0).collect(Collectors.toList());


        /* compare original button with other buttons based on
         - tag
         - childNodes (expected only text?)
         - attributes
        */

        //TODO add null checks for everything
        for (int i = 0; i < diffBtns.size(); i++) {
            if (!originalBtn.tag().equals(diffBtns.get(i).tag())) {
                //if it is not "a" tag just drop it?
                continue;
            }
            if (getButtonTextAndCleanUp(originalBtn).equals(getButtonTextAndCleanUp(diffBtns.get(i)))) {
                score.set(i, score.get(i) + 1);
            }
            //TODO put some more sophisticated logic here
            for (Attribute diffAttr : diffBtns.get(i).attributes()) {
                if (!originalBtn.attributes().get(diffAttr.getKey()).isEmpty()) {
                    if (Objects.equals(originalBtn.attributes().get(diffAttr.getKey()),
                            diffAttr.getValue())) {
                        score.set(i, score.get(i) + 1);
                    }
                }
            }


        }
        int max = 0;
        for (int i = 0; i < score.size(); i++) {
            if (score.get(i) > max) {
                max = i;
            }
        }

        Element similar = diffBtns.get(max);

        String out = "";
        out += similar.tag().getName() + getNameAndPosition(similar);
        Element parent = similar.parent();
        do {
            out = parent.tag().getName() + (getNameAndPosition(parent)) + "/" + out;
            parent = parent.parent();
        } while (parent.hasParent());


        System.out.println(out);
    }

    private static String getNameAndPosition(Element similar) {
        return similar.elementSiblingIndex() == 0 ? "" : "[" + similar.elementSiblingIndex() + "]";
    }

    private static String getButtonTextAndCleanUp(Element originalBtn) {
        return originalBtn.childNodes().iterator().next().toString().toLowerCase().trim();
    }
}
