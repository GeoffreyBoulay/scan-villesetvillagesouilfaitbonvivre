package com.github.GeoffreyBoulay.parser;

import com.github.GeoffreyBoulay.model.Position;
import com.github.GeoffreyBoulay.model.TownInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TownPageParser {

    private Pattern positionPattern = Pattern.compile("^(.*) (\\d+) / ([\\d ]+)$");
    private Pattern labelPattern = Pattern.compile("^Vivre à (.*) - Villes et villages où il fait bon vivre$");

    public TownInfo parse(String url) throws IOException {
        Document document = Jsoup.connect(url)
                .header("user-agent", "googlebot")
                .get();

        return new TownInfo(extractLabel(document),
                extractPosition(document, ".ranking-card:not(.mt-4)"),
                extractPosition(document, ".ranking-card-yellow:not(.mt-4)"),
                extractPosition(document, ".ranking-card.mt-4"),
                extractPosition(document, ".ranking-card-yellow.mt-4")

        );

    }

    private String extractLabel(Document document) {
       String title = document.title();
       assert title.startsWith("Vivre à ");
       assert title.endsWith("- Villes et villages où il fait bon vivre");
       return title
               .replaceAll("^Vivre à ", "")
               .replaceAll(" - Villes et villages où il fait bon vivre$","");
    }

    private Position extractPosition(Document document, String cssSelector) {
        Element element = document.selectFirst(cssSelector);
        Matcher matcher = positionPattern.matcher(element.text());
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid css selector: " + cssSelector);
        }
        String description = matcher.group(1);
        int index = Integer.parseInt(matcher.group(2));
        int total = Integer.parseInt(matcher.group(3).replace(" ",""));
        return new Position(description,index,total);
    }

}
