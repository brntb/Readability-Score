package com.slinger.alogrithms;

/**
 * Context class
 */
public class ReadabilityContext {

    private ReadabilityIndex index;

    public void setReadabilityIndex(ReadabilityIndex index) {
        this.index = index;
    }

    public double calculate() {
        return this.index.calculateScore();
    }

    public ReadabilityIndex getIndex() {
        return index;
    }
}
