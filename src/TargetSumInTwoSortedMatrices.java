import java.util.Timer;

/**
 * Question
 Given two "n X n" matrices which are in row and column sorted. Find count of a target sum k exists by adding one element each from both matrices
 Input:   { { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } },
 { { 10, 11, 12 }, { 13, 14, 15 }, { 16, 17, 18 } } }, Targetsum: 22
 Output: 6
 Summation possible by: 4,18
 Summation possible by: 5,17
 Summation possible by: 6,16
 Summation possible by: 7,15
 Summation possible by: 8,14
 Summation possible by: 9,13
 Required Sum 22 found :6 times
 */


public class TargetSumInTwoSortedMatrices {

    public static Integer process(Integer[][] A, Integer[][] B, Integer k){
        Integer count = 0;
        for(Integer i = 0; i < A.length; i++){
            for(Integer j = 0; j < A[i].length; j++){
                Integer a = A[i][j];
                for(Integer x = 0; x < B.length; x++){
                    if(a + B[x][0] > k){ //checking for first column element in next array
                        break;
                    }
                    for(Integer y = 0; y < B[x].length; y++){
                        Integer b = B[x][y];
                        if(a + b == k){
                            count++;
                            if(y != B[x].length - 1 && !b.equals(B[x][y + 1])){ //checking for redundant
                                break;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args){
        Integer[][] A = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Integer[][] B = { { 10, 11, 12 }, { 13, 14, 15 }, { 16, 17, 18 } };
        System.out.print(TargetSumInTwoSortedMatrices.process(A, B, 22));
    }

}
