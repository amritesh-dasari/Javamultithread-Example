package org.example;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public void AverageWordLength(String[] words, String pref){
        int len=0;
        for(int i=0;i<words.length;i++){
            len+=words[i].length();
        }
        System.out.println(pref+": Average Length of Words in File: "+len/words.length);
    }
    public void ShortestWord(String[] words, String pref){
        int worlen=100;
        String fw = "";
        for (String word : words){
            if (word.length()<worlen){
                fw=word;
                worlen=fw.length();
            }
        }
        System.out.println(pref+": Shortest Word in the File: "+fw);
        System.out.println(pref+": SW Length: "+worlen);
    }

    public void LongestWord(String[] words, String pref){
        int worlen=0;
        String fw = "";
        for (String word : words){
            if (word.length()>worlen){
                fw=word;
                worlen=fw.length();
            }
        }
        System.out.println(pref+": Longest Word in the File: "+fw);
        System.out.println(pref+": LW Length: "+worlen);
    }

    public void MostCommonWord(String[] words, String pref){
        Map<String, Integer> wordFrequencies = new HashMap<>();

        for (String word : words) {
            Integer frequency = wordFrequencies.get(word);
            if (frequency == null) {
                frequency = 0;
            }
            frequency++;
            wordFrequencies.put(word, frequency);
        }

        String mostCommonWord = null;
        int highestFrequency = 0;
        for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
            if (entry.getValue() > highestFrequency) {
                mostCommonWord = entry.getKey();
                highestFrequency = entry.getValue();
            }
        }

        System.out.println(pref+": The most common word is: " + mostCommonWord);
        System.out.println(pref+": Frequency: "+highestFrequency);
    }
}
