import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MainMain {

    public static String readFile(String filename) {
        try {
            StringBuilder res = new StringBuilder();
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
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
        HashMap<Character, Integer> frequency = countWords(sentence);
        frequency = sortFrequency(frequency);
        printFrequency(frequency);
    }

    public static HashMap<Character, Integer> countWords(String sentence) {
        HashMap<Character, Integer> res = new HashMap<>();
        for (int i = 0; i < sentence.length(); i++) {
            char leter = sentence.charAt(i);
            if (Character.isLetter(leter)) {
                Integer frequency = res.get(leter);
                res.put(leter, frequency == null ? 1 : frequency + 1);
            }
        }
        return res;
    }

    public static HashMap<Character, Integer> sortFrequency(HashMap<Character, Integer> frequency)
    {
        List<Map.Entry<Character, Integer> > list = new LinkedList<Map.Entry<Character, Integer> >(frequency.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<Character, Integer> res = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> aa : list) {
            res.put(aa.getKey(), aa.getValue());
        }

        return res;
    }

    public static void printFrequency(HashMap<Character, Integer> frequency) {
        for (Character name: frequency.keySet()){
            String key = name.toString();
            String value = frequency.get(name).toString();
            System.out.println(key + " - " + value);
        }
    }

    public static void main(String[] args) {
        String test = "a a a b b c dd d d";
        //String test = readFile("test.txt");
        countWordsAndPrintFrecuency(test);
    }
}
