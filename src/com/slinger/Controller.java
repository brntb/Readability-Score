package com.slinger;

import com.slinger.alogrithms.*;
import java.util.Scanner;

import static com.slinger.Main.singletonHelper;

public class Controller {

    private final String text;
    private final Scanner scanner;
    private final ReadabilityContext context;


    public Controller(String text, Scanner scanner) {
        this.text = text;
        this.scanner = scanner;
        this.context = new ReadabilityContext();
    }

    public void run() {
        printTextInfo();
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String toCalculate = scanner.nextLine().toUpperCase().trim();
        System.out.println();

        switch (toCalculate) {
            case "ARI":
                context.setReadabilityIndex(new AutomatedReadabilityIndex(text));
                printResult(context.getIndex(), context.calculate());
                break;

            case "FK":
                context.setReadabilityIndex(new FleschKincaidReadabilityIndex(text));
                printResult(context.getIndex(), context.calculate());
                break;

            case "SMOG":
                context.setReadabilityIndex(new SMOGIndex(text));
                printResult(context.getIndex(), context.calculate());
                break;

            case "CL":
                context.setReadabilityIndex(new ColemanLiauIndex(text));
                printResult(context.getIndex(), context.calculate());
                break;

            case "ALL":
                printAll();
                break;

            default:
                System.out.println("Unknown command!");
        }

    }

    private void printTextInfo() {
        System.out.println("The text is:\n" + text + "\n");
        System.out.println("Words: " + singletonHelper.getWordCount(text));
        System.out.println("Sentences: " + singletonHelper.getSentenceCount(text));
        System.out.println("Characters: " + singletonHelper.getCharacterCount(text));
        System.out.println("Syllables: " + singletonHelper.getSyllableCount(text));
        System.out.println("Polysyllables: " + singletonHelper.getPolySyllableCount(text));
    }

    private int translateScoreToAge(double index) {
        int score = (int) Math.round(index);
        if (score < 3) {
            return (score + 5);
        } else if (score < 13) {
            return score + 6;
        } else if (score < 14) {
            return score + 11;
        } else {
            return 30;
        }
    }

    private void printAll() {
        double sum = 0;

        context.setReadabilityIndex(new AutomatedReadabilityIndex(text));
        sum += translateScoreToAge(context.calculate());
        printResult(context.getIndex(), context.calculate());

        context.setReadabilityIndex(new FleschKincaidReadabilityIndex(text));
        sum += translateScoreToAge(context.calculate());
        printResult(context.getIndex(), context.calculate());

        context.setReadabilityIndex(new SMOGIndex(text));
        sum += translateScoreToAge(context.calculate());
        printResult(context.getIndex(), context.calculate());

        context.setReadabilityIndex(new ColemanLiauIndex(text));
        sum += translateScoreToAge(context.calculate());
        printResult(context.getIndex(), context.calculate());

        double average = sum / 4;


        System.out.println("\nThis text should be understood in average by " + average + "-year-olds.");
    }

    private void printResult(ReadabilityIndex index, double score) {
        String ageSuggestion = " (about " + translateScoreToAge(score) + "-year-olds).";
        System.out.println(index.getTestName() + String.format("%.2f", score) + ageSuggestion);
    }

}
