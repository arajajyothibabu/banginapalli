import java.util.ArrayList;

/**
 * Question
 Given a string. Return missing characters in the string to make it a magic string
 Magic String: A String is said to magic string if it contains all the english alphabets-case insensitive
 Input: "I am from Bhimavaram"
 Ouptut: "cdegjklnpqstuwxyz"

 Input:"abceghijkmnopqruv"
 Output:"xyz"
 */

public class MagicString {

    public static String missingCharsToMakeMagicString(String input){
        ArrayList<Character> alphabets = new ArrayList<Character>();
        for(Integer i = 97; i < 123; i++){
            alphabets.add(Character.toChars(i)[0]);
        }
        for(Integer i = 0; i < input.length(); i++){
            alphabets.remove(new Character(input.charAt(i)));
        }
        return alphabets.toString().replaceAll("[,\\s\\[\\]]", "");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(missingCharsToMakeMagicString("abceghijkmnopqruv"));
        System.out.println(missingCharsToMakeMagicString("I am from Bhimavaram"));
    }

}
