
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static String readFile(String filename) {
        try {
            StringBuilder res = new StringBuilder();
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine() + " ";
                res.append(data);
            }
            myReader.close();
            return res.toString();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "";
        }
    }

    public static void countWordsAndPrintFrecuency(String sentence) {
        HashMap<String, Integer> frequency = countWords(sentence);
        frequency = sortFrequency(frequency);
        printFrequency(frequency);
    }

    public static HashMap<String, Integer> countWords(String sentence) {
        HashMap<String, Integer> res = new HashMap<>();
        String[] words = sentence.split(" ");
        for (String word : words) {
            Integer frequency = res.get(word);
            res.put(word, frequency == null ? 1 : frequency + 1);
        }
        return res;
    }

    public static HashMap<String, Integer> sortFrequency(HashMap<String, Integer> frequency)
    {
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(frequency.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<String, Integer> res = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            res.put(aa.getKey(), aa.getValue());
        }

        return res;
    }

    public static void printFrequency(HashMap<String, Integer> frequency) {
        try(FileWriter writer = new FileWriter("frequency.txt", false)) {
            for (String name: frequency.keySet()){
                String key = name.toString();
                String value = frequency.get(name).toString();
                System.out.println(key + " - " + value);
                writer.write(key + " - " + value + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //String test = "a a a b b c d d d d";
        String test = readFile("test.txt");
        countWordsAndPrintFrecuency(test);
    }
}