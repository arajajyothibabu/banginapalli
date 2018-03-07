/**
 * Question
 Given a m X n Sorted Matrix(rowwise and columnwise): find k in m+n time complexity
 */

public class KInSortedMatrix {

    static Boolean containsK(Integer[][] matrix, Integer k){
        for(Integer i = 0; i < matrix.length; i++){
            for(Integer j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] > k){
                    break;
                }else if(matrix[i][j].equals(k)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Integer[][] A = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Integer[][] B = { { 10, 11 }, { 13, 14 }, { 16, 17 } };
        System.out.println(containsK(A, 9));
        System.out.println(containsK(A, 100));
        System.out.println(containsK(B, 16));
        System.out.println(containsK(B, -1));
    }

}
