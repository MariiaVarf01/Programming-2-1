import java.util.*;

public class Main {
    // Function calculate the number of all the characters except spaces
    public static Integer countCharacters (String text) {
        String textWithoutSpaces = text.replaceAll(" ", "");

        return textWithoutSpaces.length();
    }

    // Function calculate the number of all the words in the text
    public static Integer countWords (String text) {
        String[] words = text.split("[,;\\-+\\s]+");

        return words.length;
    }

    // Function sort HashMap of chars and integers in desc order
    public static HashMap<Character, Integer> sortDesByValue(HashMap<Character, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Character, Integer> > list =
                new LinkedList<Map.Entry<Character, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
            public int compare(Map.Entry<Character, Integer> o2,
                               Map.Entry<Character, Integer> o1)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    // Function returns the HashMap consists of characters and their frequency
    public static HashMap<Character, Integer> charsFrequencyHashMap (String text) {
        String textWithoutSpaces = text.replaceAll(" ", "");
        HashMap<Character, Integer> charsFrequencyMap = new HashMap<Character, Integer>();

        for (int i = 0; i < textWithoutSpaces.length(); i++) {
            Integer newValue = charsFrequencyMap.containsKey(textWithoutSpaces.charAt(i)) ? charsFrequencyMap.get(textWithoutSpaces.charAt(i)) + 1 : 1;
            charsFrequencyMap.put(textWithoutSpaces.charAt(i), newValue);
        }

        return charsFrequencyMap;
    }

    // Function returns the HashMap consists of words and their frequency
    public static HashMap<String, Integer> wordsFrequencyHashMap (String text) {
        String[] lowerCaseText = text.split("[^\\w'-]+");
        HashMap<String, Integer> wordsFrequencyMap = new HashMap<String, Integer>();

        for (int i = 0; i < lowerCaseText.length; i++) {
            Integer newValue = wordsFrequencyMap.containsKey(lowerCaseText[i]) ? wordsFrequencyMap.get(lowerCaseText[i]) + 1 : 1;
            wordsFrequencyMap.put(lowerCaseText[i], newValue);
        }

        return wordsFrequencyMap;
    }

    // Function returns the most common char. In case of a tie, select any of the tied characters.
    public static Character findMostCommonCharacter (String text) {
        HashMap <Character, Integer> characterIntegerHashMap = charsFrequencyHashMap(text);

        return sortDesByValue(characterIntegerHashMap).keySet().iterator().next();
    }

    // Function returns the frequency of provided char or 0, if the char doesn't exist in the text
    public static Integer findCharacterFrequency (String text, Character character) {
        HashMap <Character, Integer> characterFrequencyHashMap = charsFrequencyHashMap(text);

        return characterFrequencyHashMap.getOrDefault(character, 0);
    }

    // Function returns the frequency of provided word or 0, if the word doesn't exist in the text
    public static Integer findWordFrequency (String text, String word) {
        HashMap <String, Integer> wordIntegerHashMap = wordsFrequencyHashMap(text);

        return wordIntegerHashMap.getOrDefault(word, 0);
    }

    // Function returns the number of words that are unique in the text or 0
    public static Integer findNumberOfUniqueWords (String text) {
        HashMap <String, Integer> wordIntegerHashMap = wordsFrequencyHashMap(text);

        return (int) wordIntegerHashMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .count();
    }

    // Function prints a message and gets text from user and returns it
    public static String getString (String msg, Scanner sc) {
        System.out.print(msg);
        String text = sc.nextLine().trim().toLowerCase();

        if (text.isEmpty()) {
            System.out.println("Incorrect input, please try again.");
            return getString(msg, sc); // Return the corrected input
        }

        return text;
    }

    // Function prints a message and gets char from user and returns it
    public static Character getChar (String msg, Scanner sc) {
        System.out.print(msg);
        String text = sc.nextLine().trim().toLowerCase();

        if (text.length() != 1) {
            System.out.println("Incorrect input, please try again.");
            return getChar(msg, sc);
        }

        return text.charAt(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String text = getString("1. Input your a paragraph or a lengthy text: ", sc);

            System.out.println("2. Total number of characters: " + countCharacters(text));
            System.out.println("3. Total number of words: " + countWords(text));
            System.out.println("4. Most Common Character: " + findMostCommonCharacter(text));

            char userChar = getChar("5. Input a character: ", sc);
            System.out.println("Frequency of occurrences of character '" + userChar + "' : " + findCharacterFrequency(text, userChar));

            String userWord = getString("6. Input a word: ", sc);
            System.out.println("Frequency of occurrences of word '" + userWord + "' : " + findWordFrequency(text, userWord));

            System.out.print("7. Number of unique words: " + findNumberOfUniqueWords(text));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            sc.close();
        }
    }
}
