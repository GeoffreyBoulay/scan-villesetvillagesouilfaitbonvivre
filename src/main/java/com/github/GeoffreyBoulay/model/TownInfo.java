package com.github.GeoffreyBoulay.model;

public record TownInfo(
        String label,
        Position national,
        Position departemental,
        Position nationalCategory,
        Position departementalCategory
) {
}
