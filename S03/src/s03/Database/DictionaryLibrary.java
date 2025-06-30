/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s03.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import s03.ValidInput;

/**
 * S03-English – English dictionary
 *
 * @author Vo Quoc An - ce190460
 * @since 2025-06-01
 */
public class DictionaryLibrary {

    private HashMap<Character, HashMap<String, String>> dictionary = new HashMap<>();
    private ReadAndSave ras = new ReadAndSave();

    /**
     * Constructor for the `DictionaryLibrary` class. This constructor is
     * responsible for initializing the dictionary data.
     */
    public DictionaryLibrary() {
        dictionary = load();
    }

    /**
     * Retrieves the entire dictionary data structure. This method provides
     * direct access to the internal representation of the dictionary.
     *
     * @return A HashMap where the outer key is a Character (presumably the
     * first letter of a word), and the value is another HashMap. The inner
     * HashMap's key is a String (the vocabulary word), and its value is a
     * String (the meaning of that word).
     */
    public HashMap<Character, HashMap<String, String>> getDictionary() {
        return dictionary;
    }

    /**
     * Sets the entire dictionary data structure. This method allows replacing
     * the current dictionary with a new one. It's typically used when loading
     * dictionary data from an external source or resetting the dictionary's
     * state.
     *
     * @param dictionary A HashMap where the outer key is a Character (the first
     * letter of a word), and the value is another HashMap. The inner HashMap's
     * key is a String (the vocabulary word), and its value is a String (the
     * meaning of that word).
     */
    public void setDictionary(HashMap<Character, HashMap<String, String>> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Loads dictionary data from predefined files into a HashMap structure.
     * This method iterates through all letters of the alphabet (a-z),
     * attempting to load vocabulary words and their corresponding meanings from
     * files named like "a_index.dat" and "a_meaning.dat", "b_index.dat" and
     * "b_meaning.dat", and so on.
     *
     * @return A HashMap representing the loaded dictionary. The outer key is a
     * character (the starting letter of words), and the value is another
     * HashMap. The inner HashMap stores vocabulary words (String) as keys and
     * their meanings (String) as values.
     */
    public HashMap<Character, HashMap<String, String>> load() {
        HashMap<Character, HashMap<String, String>> dictionary = new HashMap<>();
        char letter = 'a';
        String fileNameIndex = "";
        String fileNameMean = "";
        while (true) {
            HashMap<String, String> tempDictionary = new HashMap<>();
            if (letter > 'z') {
                break;
            }
            fileNameIndex = "data/" + letter + "_index.dat";
            fileNameMean = "data/" + letter + "_meaning.dat";
            ArrayList<String> index = ras.readFile(fileNameIndex);
            ArrayList<String> mean = ras.readFile(fileNameMean);
            for (int i = 0; i < index.size(); i++) {
                tempDictionary.put(index.get(i), mean.get(i));
            }
            dictionary.put(letter, tempDictionary);
            letter += 1;
        }
        return dictionary;
    }

    /**
     * Updates the persistent storage (files) with the current state of the
     * in-memory dictionary. This method iterates through each letter of the
     * alphabet (a-z), retrieves the words and their meanings associated with
     * that letter from the `dictionary` HashMap, and then writes these back to
     * their respective index and meaning files (e.g., "a_index.dat",
     * "a_meaning.dat").
     */
    public void update() {
        char letter = 'a';
        String fileNameIndex = "";
        String fileNameMean = "";
        while (true) {
            if (letter > 'z') {
                break;
            }
            fileNameIndex = "data/" + letter + "_index.dat";
            fileNameMean = "data/" + letter + "_meaning.dat";
            ArrayList<String> index = new ArrayList<>(dictionary.get(letter).keySet());
            ArrayList<String> meanning = new ArrayList<>(dictionary.get(letter).values());
            ras.writeFile(index, fileNameIndex);
            ras.writeFile(meanning, fileNameMean);
            letter += 1;
        }
    }

    /**
     * Allows the user to create and add a new word along with its meaning to
     * the dictionary. This method first prompts for the new word, checks if it
     * already exists, then prompts for its meaning, and finally adds it to the
     * in-memory dictionary and persists the changes to files.
     */
    public void create() {
        String newVocabulary = ValidInput.vocabulary("Enter a new word: ");
        char firstLetter = newVocabulary.charAt(0);
        char letter = 'a';
        while (true) {
            if (letter > 'z') {
                break;
            }
            if (dictionary.get(letter).containsKey(newVocabulary)) {
                System.out.println("**********************");
                System.out.println("This word is already exists!");
                System.out.println("**********************");
                return;
            }
            letter += 1;
        }
        String meanning = ValidInput.meaning("Meaning: ");
        dictionary.get(firstLetter).put(newVocabulary, meanning);
        update();
    }

    /**
     * Allows the user to edit the meaning of an existing word in the
     * dictionary. The method prompts for a word to update, searches for it
     * across all alphabetical sections, and if found, prompts for a new meaning
     * to replace the old one. If the word is not found, an appropriate message
     * is displayed.
     */
    public void edit() {
        String vocabulary = ValidInput.vocabulary("Enter a word to update: ");
        char letter = 'a';
        while (true) {
            if (letter > 'z') {
                break;
            }
            if (dictionary.get(letter).containsKey(vocabulary)) {
                String editing = ValidInput.meaning("Meaning: ");
                dictionary.get(vocabulary.toLowerCase().charAt(0)).put(vocabulary, editing);
                update();
                return;
            }
            letter += 1;
        }
        System.out.println("**********************");
        System.out.println("This word does not exist!");
        System.out.println("**********************");

    }

    /**
     * Allows the user to look up the meaning of a word in the dictionary. This
     * method prompts for a word and then attempts to retrieve and display its
     * meaning. It assumes the word exists in the dictionary and handles the
     * display of its meaning.
     *
     * Note: This implementation assumes the word will always be found. A more
     * robust implementation would include error handling (e.g., checking for
     * null) if the word does not exist in the dictionary, to prevent a
     * NullPointerException.
     */
    public void lookUp() {
        String vocabulary = ValidInput.vocabulary("Enter a word to look up: ");
        // Check if the dictionary contains the first letter of the input word (converted to lowercase).
        // If the first letter is not a key in the main dictionary, it means no words starting with that letter exist.
        if (!dictionary.get(vocabulary.toLowerCase().charAt(0)).containsKey(vocabulary)) {
            System.out.println("**********************");
            System.out.println("This word does not exist!");
            System.out.println("**********************");
            return;
        }
        System.out.printf("Meaning: %s\n", dictionary.get(vocabulary.toLowerCase().charAt(0)).get(vocabulary));
    }

    /**
     * Prints out the entire contents of the dictionary to the console. This
     * method iterates through each alphabetical section of the dictionary and
     * prints the string representation of each sub-dictionary (HashMap) for
     * every letter from 'a' to 'z'.
     */
    public void prinOut() {
        // Initialize a character variable 'letter' starting from 'a'.
        char letter = 'a';
        // Start an infinite loop to iterate through letters from 'a' to 'z'.
        while (true) {
            // Check if the current 'letter' has gone past 'z'.
            // If it has, it means all letters have been processed, so break out of the loop.
            if (letter > 'z') {
                break;
            }
            System.out.println(dictionary.get(letter).toString());
            // Increment the character to move to the next letter in the alphabet.
            letter += 1;
        }
    }

    /**
     * Destroys all dictionary data by clearing the contents of all associated
     * data files. This method iterates through each letter of the alphabet
     * (a-z) and attempts to clear the content of both the index (vocabulary)
     * and meaning files for that letter. After this method executes, the
     * persistent dictionary storage will be empty.
     */
    public void destroy() {
        // Initialize a character variable 'letter' starting from 'a'.
        char letter = 'a';
        String fileNameIndex = "";
        String fileNameMean = "";
        // Start an infinite loop to iterate through letters from 'a' to 'z'.
        while (true) {
            // Check if the current 'letter' has gone past 'z'.
            // If it has, it means all letters have been processed, so break out of the loop.
            if (letter > 'z') {
                break;
            }
            fileNameIndex = "data/" + letter + "_index.dat";
            fileNameMean = "data/" + letter + "_meaning.dat";
            ras.clear(fileNameIndex);
            ras.clear(fileNameMean);
            // Increment the character to move to the next letter in the alphabet.
            letter += 1;
        }
    }
}
