package com.br.scheduler.util;

import java.util.Random;

public class RandomNameGenerator {

    private static final String[] names = {"dome",
            "drag",
            "pier",
            "vain",
            "fare",
            "home",
            "seem",
            "fund",
            "step",
            "chop",
            "flag",
            "chew",
            "loan",
            "tell",
            "make",
            "stay",
            "wall",
            "view",
            "tone",
            "blue",
            "heel",
            "beef",
            "burn",
            "fame",
            "west",
            "read",
            "door",
            "grow",
            "bulb",
            "belt",
            "push",
            "bear",
            "rent",
            "fork",
            "week",
            "mail",
            "dose",
            "harm",
            "gain",
            "side",
            "plot",
            "fill",
            "flex",
            "boat",
            "lick",
            "hide",
            "slab",
            "norm",
            "seat",
            "gasp"};

    public static String generate() {
        Random random = new Random();
        return names[random.nextInt(0, 50)];
    }
}
