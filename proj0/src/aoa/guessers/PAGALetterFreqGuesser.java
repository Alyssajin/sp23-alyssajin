package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }
    /** takes as input a list of words and a pattern, and only returns words that match. */
    public List<String> keepOnlyWordsThatMatchPattern(String pattern, List<String> words, List<Character> guesses) {
        List<String> wordsMatchPattern = new ArrayList<>();

        boolean abort = false;
        for (String word : words) {
            if (word.length() != pattern.length()) {
                abort = true;
            } else {
                for (int i = 0; i < pattern.length(); i++ ) {
                    if (pattern.charAt(i) != '-') {
                        if (word.charAt(i) != pattern.charAt(i)) {
                            abort = true;
                        }
                    } else {
                        for (char g : guesses) {
                            if (word.charAt(i) == g) {
                                abort = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!abort) {
                wordsMatchPattern.add(word);
            }
            abort = false;
        }
        return wordsMatchPattern;
    }


    /** returns a frequency map like the one in the example above ({a=2, b=1, d=1, e=2, l=1, t=1}). */
    public Map<Character, Integer> getFreqMapThatMatchesPattern(List<String> wordsMatchPattern) {
        Map<Character, Integer> frequencyMap = new TreeMap<>();
        for (String word: wordsMatchPattern) {
            for (char letter: word.toCharArray()) {
                if (frequencyMap.containsKey(letter)) {
                    frequencyMap.put(letter, frequencyMap.get(letter) + 1);
                } else {
                    frequencyMap.put(letter, 1);
                }
            }
        }
        return frequencyMap;
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        int max = 0;
        char maxLetter = ' ';
        List<String> wordsMatchPattern = keepOnlyWordsThatMatchPattern(pattern, words, guesses);
        Map<Character, Integer> frequencyMap = getFreqMapThatMatchesPattern(wordsMatchPattern);
        for (char letter: frequencyMap.keySet()) {
            if (!guesses.contains(letter)) {
                if (frequencyMap.get(letter) > max) {
                    max = frequencyMap.get(letter);
                    maxLetter = letter;
                }
            }
        }
        return maxLetter;
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
