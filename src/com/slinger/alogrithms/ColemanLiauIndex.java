package com.slinger.alogrithms;

import static com.slinger.Main.singletonHelper;

public class ColemanLiauIndex implements ReadabilityIndex {

    private final String text;

    public ColemanLiauIndex(String text) {
        this.text = text;
    }

    @Override
    public double calculateScore() {
        int words = singletonHelper.getWordCount(text);
        int sentences = singletonHelper.getSentenceCount(text);
        int characters = singletonHelper.getCharacterCount(text);

        //L is the average number of characters per 100 words
        double L = (1.0 * characters) / words * 100;

        // S is the average number of sentences per 100 words
        double S = (1.0 * sentences) / words * 100;

        return 0.0588 * L - 0.296 * S - 15.8;
    }

    @Override
    public String getTestName() {
        return "Coleman-Liau index: ";
    }
}
