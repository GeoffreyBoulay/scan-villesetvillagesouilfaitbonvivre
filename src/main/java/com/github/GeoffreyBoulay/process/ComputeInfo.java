package com.github.GeoffreyBoulay.process;

import com.github.GeoffreyBoulay.model.TownInfo;
import com.github.GeoffreyBoulay.parser.DepartementPageParser;
import com.github.GeoffreyBoulay.parser.TownPageParser;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ComputeInfo {

    private final TownPageParser townPageParser;

    private final DepartementPageParser departementPageParser;

    private final String baseUrl = "https://www.villesetvillagesouilfaitbonvivre.com";

    public ComputeInfo() throws IOException {
        this(
                new TownPageParser(),
                new DepartementPageParser()
        );
    }

    ComputeInfo(TownPageParser townPageParser, DepartementPageParser departementPageParser) {
        this.townPageParser = townPageParser;
        this.departementPageParser = departementPageParser;
    }

    public void processSortAndLogDepartement(String departementPath) throws IOException {
        getTowns(departementPath)
                .stream()
                .sorted(Comparator.comparingInt(town -> town.national().index()))
                .map(townInfo -> String.format("%d - %d - %s",
                        townInfo.departemental().index(),
                        townInfo.national().index(),
                       townInfo.label())
                )
                .forEachOrdered(System.out::println);
    }

    public List<TownInfo> getTowns(String departementPath) throws IOException {
        return departementPageParser.getCityUrl(baseUrl + departementPath)
                .stream()
                .map(this::absoluteUrl)
                .map(this::parseTown)
                .toList();
    }

    private TownInfo parseTown(String url) {
        try {
            return townPageParser.parse(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String absoluteUrl(String url) {
        if (url.startsWith("/")) {
            return baseUrl + url;
        }
        if (url.startsWith("https://")) {
            return baseUrl + url;
        }
        throw new IllegalArgumentException("Invalid URL: " + url);
    }

}
