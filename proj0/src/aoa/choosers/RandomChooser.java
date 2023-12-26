package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        if (wordLength < 1) {
            throw new IllegalArgumentException();
        }
        List<String> words = aoa.utils.FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = words.size();
        if (numWords == 0) {
            throw new IllegalStateException();
        } else {
            int randomlyChosenWordNumber = StdRandom.uniform(numWords);
            chosenWord = words.get(randomlyChosenWordNumber);
        }
        pattern = "-".repeat(wordLength);
    }
    /**  return the number of occurrences of the guessed letter in the secret word. */
    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        int occurrence = 0;
        for (int i = 0; i < chosenWord.length(); i ++) {
            if (letter == chosenWord.charAt(i)) {
                occurrence += 1;
                pattern = pattern + letter;
            }
        }
        return occurrence;
    }
    /** return the current pattern to be displayed for the game using the guesses that have been made.*/
    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        List<String> patternList = new ArrayList<>();
        for(int i = 0; i < chosenWord.length(); i ++) {
            if (pattern.indexOf(chosenWord.charAt(i)) < 0) {
                patternList.add("-");
            } else {
                patternList.add(String.valueOf(chosenWord.charAt(i)));
            }
        }
        pattern = String.join("", patternList);
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return chosenWord;
    }

}
