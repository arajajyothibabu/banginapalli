/**
 * Question
 Given an array. Print max element to the right for each element in the array
 Input : [7, 6, 5, 4, 3, 2, 1]
 Output: [6, 5, 4, 3, 2, 1,  ]
 */

public class MaxToTheRightInArray {

    static Integer[] process(Integer[] array){
        Integer[] output = new Integer[0];
        if(array.length > 1) { //only array with at least 2 elements
            output = new Integer[array.length - 1];
            Integer max = array[array.length - 1];
            for (Integer i = array.length - 1; i > 0; i--) {
                if (max <= array[i]) {
                    max = array[i];
                    //System.out.print(max + " ");
                    output[array.length - 1 - i] = max;
                }
            }
        }
        for(Integer i = 0; i <= output.length / 2; i++){ //reversing the array
            Integer temp = output[i];
            output[i] = output[output.length - 1 - i];
            output[output.length - 1 - i] = temp;
        }
        return output;
    }

    public static void main(String[] args){
        Integer[] array = { 7, 6, 5, 4, 3, 2, 1 };
        Integer[] output = process(array);
        for(Integer i = 0; i < output.length; i++){
            System.out.print(output[i] + " ");
        }
    }

}
