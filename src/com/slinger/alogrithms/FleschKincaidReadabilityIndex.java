package com.slinger.alogrithms;

import static com.slinger.Main.singletonHelper;

/**
 * Concrete Strategy
 */
public class FleschKincaidReadabilityIndex implements ReadabilityIndex {

    private final String text;

    public FleschKincaidReadabilityIndex(String text) {
        this.text = text;
    }

    @Override
    public double calculateScore() {
        int words = singletonHelper.getWordCount(text);
        int sentences = singletonHelper.getSentenceCount(text);
        int syllables = singletonHelper.getSyllableCount(text);

        double wordsDividedBySentences = (1.0 * words) / sentences;
        double syllablesDividedByWords = (1.0 * syllables) / words;

        return (0.39 * wordsDividedBySentences) + (11.8 * syllablesDividedByWords) - 15.59;
    }

    @Override
    public String getTestName() {
        return "Flesch–Kincaid readability tests: ";
    }
}
