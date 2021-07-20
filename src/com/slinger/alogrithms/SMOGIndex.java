package com.slinger.alogrithms;

import static com.slinger.Main.singletonHelper;

public class SMOGIndex implements ReadabilityIndex {

    private final String text;

    public SMOGIndex(String text) {
        this.text = text;
    }

    @Override
    public double calculateScore() {
        int polySyllableCount = singletonHelper.getPolySyllableCount(text);
        int sentenceCount = singletonHelper.getSentenceCount(text);

        double innerExpression = polySyllableCount * (30 / sentenceCount);

        return 1.043 * Math.sqrt(innerExpression) + 3.1291;
    }

    @Override
    public String getTestName() {
        return "Simple Measure of Gobbledygook: ";
    }
}
