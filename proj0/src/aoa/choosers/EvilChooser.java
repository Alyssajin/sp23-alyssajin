package aoa.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        if (wordLength < 1) {
            throw new IllegalArgumentException();
        }
        wordPool = aoa.utils.FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = wordPool.size();
        if (numWords == 0) {
            throw new IllegalStateException();
        }
        pattern = "-".repeat(wordLength);
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        Map<String, List<String>> patternFamily = new TreeMap<>();
        int maxNumber = 0;
        int occurrences = 0; // the number of occurrences of the guessed letter in the new pattern.
        String patternChosen = "";
        for (String word : wordPool) {
            List<String> guessPatternList = new ArrayList<>(List.of(pattern.split("")));
            for (int i = 0; i < word.length(); i ++) {
                if (word.charAt(i) == letter) {
                    guessPatternList.set(i, String.valueOf(letter));
                }
            }
            String guessPattern = String.join("", guessPatternList);
            // if the key is in the map, append its related value list.
            if (patternFamily.containsKey(guessPattern)) {
                List<String> valueList = new ArrayList<>(patternFamily.get(guessPattern));
                valueList.add(word);
                patternFamily.put(guessPattern, valueList);
                // if not, put the new key-value list into the map.
            } else {
                patternFamily.put(guessPattern, List.of(word));
            }
        }
        for (String p : patternFamily.keySet()) {
            int occurrence = patternFamily.get(p).size();
            if (occurrence > maxNumber) {
                maxNumber = occurrence;
                patternChosen = p;
            }
        }
        pattern = patternChosen;
        wordPool = patternFamily.get(patternChosen);
        // the number of occurrences of guessed letter in the pattern.
        for (char l : pattern.toCharArray()) {
            if (l == letter) {
                occurrences += 1;
            }
        }
        return occurrences;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        int numWords = wordPool.size();
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        return wordPool.get(randomlyChosenWordNumber);
    }
}
