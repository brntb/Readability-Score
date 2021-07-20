package com.slinger.alogrithms;

import static com.slinger.Main.singletonHelper;

/**
 * Concrete Strategy
 */
public class AutomatedReadabilityIndex implements ReadabilityIndex {

    private final String text;

    public AutomatedReadabilityIndex(String text) {
        this.text = text;
    }

    @Override
    public double calculateScore() {
        int words = singletonHelper.getWordCount(text);
        int sentences = singletonHelper.getSentenceCount(text);
        int characters = singletonHelper.getCharacterCount(text);

        double charactersDividedByWords = (1.0 * characters) / words;
        double wordsDividedBySentences = (1.0 * words / sentences);

       return  4.71 * charactersDividedByWords + .5 * wordsDividedBySentences - 21.43;
    }

    @Override
    public String getTestName() {
        return "Automated Readability Index: ";
    }
}
