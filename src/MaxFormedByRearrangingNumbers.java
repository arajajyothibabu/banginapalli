import java.util.Arrays;

/**
 * Question
 Given an array of numbers: Find maximum number formed by re-arranging the numbers
 Input : [1234, 123, 1234, 5]
 Output : Max number formed: 512341234123
 */

public class MaxFormedByRearrangingNumbers {

    static Integer digitsInNumber(Integer input) {
        Integer count = 0;
        while(input > 0){
            count++;
            input /= 10;
        }
        return count;
    }

    static Integer numberWithFirstNDigitsOfNumber(Integer input, Integer n) {
        Integer digitsInNumber = digitsInNumber(input);
        if(digitsInNumber >= n){
            return input / (int)Math.pow(10, digitsInNumber - n);
        }
        return 0;
    }

    static Integer[] customSort(Integer[] input) {
        for(Integer i = 0; i < input.length; i++){
            for(Integer j = i + 1; j < input.length; j++){
                Integer first = input[i], next = input[j];
                Integer digitsInFirst = digitsInNumber(first);
                Integer digitsInNext = digitsInNumber(next);
                if(digitsInFirst.equals(digitsInNext)){
                    if(first < next){
                        input[i] = next;
                        input[j] = first;
                    }
                }else if(digitsInFirst > digitsInNext){
                    Integer subNumber = numberWithFirstNDigitsOfNumber(first, digitsInNext);
                    if(subNumber < next){
                        input[i] = next;
                        input[j] = first;
                    }
                }else{
                    Integer subNumber = numberWithFirstNDigitsOfNumber(next, digitsInFirst);
                    if(subNumber >= first){
                        input[i] = next;
                        input[j] = first;
                    }
                }
            }
        }
        /*for(Integer i = 0; i < input.length; i++){
            System.out.print(input[i] + " ");
        }
        System.out.println();*/
        return input;
    }

    static void maxNumber(Integer[] input) {
        System.out.println(Arrays.toString(customSort(input)).replaceAll("[,\\s\\[\\]]", ""));
    }

    public static void main(String[] args){
        Integer[] A = { 1234, 123, 1234, 5 };
        Integer[] B = { 456, 1245, 763, 89, 9, 123 };
        maxNumber(A);
        maxNumber(B);
    }

}
