package com.github.GeoffreyBoulay.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public class DepartementPageParser {


    public List<String> getCityUrl(String url) throws IOException {
        Document document = Jsoup.connect(url)
                .header("user-agent", "googlebot")
                .get();

        return document.select(".city-item a")
                .stream()
                .map(anchor -> anchor.attr("href"))
                .toList();
    }

}
