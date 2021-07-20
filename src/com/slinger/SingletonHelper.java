package com.slinger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingletonHelper {

    private static SingletonHelper INSTANCE;
    private int wordCount;
    private int sentenceCount;
    private int characterCount;
    private int syllableCount;
    private int polySyllableCount;

    public static SingletonHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonHelper();
        }
        return INSTANCE;
    }

    public int getWordCount(String text) {
        //dont recalculate if method has already been called
        if (wordCount != 0) {
            return wordCount;
        }

        wordCount = text.split("\\s+").length;
        return wordCount;
    }

    public int getSentenceCount(String text) {
        if (sentenceCount != 0) {
            return sentenceCount;
        }

        sentenceCount = text.split("[.?!]").length;
        return sentenceCount;
    }

    public int getCharacterCount(String text) {
        if (characterCount != 0) {
            return  characterCount;
        }

        characterCount = text.replaceAll("\\s+", "").length();
        return characterCount;
    }

    /**
     *  Syllables are as follows:
     * 1. Count the number of vowels in the word.
     * 2. Do not count double-vowels (for example, "rain" has 2 vowels but only 1 syllable).
     * 3. If the last letter in the word is 'e' do not count it as a vowel (for example, "side" has 1 syllable).
     * 4. If at the end it turns out that the word contains 0 vowels, then consider this word as a 1-syllable one.
     */
    public int getSyllableCount(String text) {
        if (syllableCount != 0) {
            return syllableCount;
        }

        text = text.toLowerCase();

        for (String word : text.split("[\\s]+")) {

            Pattern pattern = Pattern.compile("[aeiouy][^aeiouy\\s]|[aiouy]$");
            Matcher matcher = pattern.matcher(word);

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            //mine as well calculate polysyllables here to save another for loop
            polySyllableCount += count > 2 ? 1 : 0;
            syllableCount += count > 0 ? count : 1;
        }

        return syllableCount;
    }

    //polysyllables are calculated with syllables
    public int getPolySyllableCount(String text) {
        if (polySyllableCount != 0) {
            return polySyllableCount;
        }

        polySyllableCount = getSyllableCount(text);
        return polySyllableCount;
    }

}
