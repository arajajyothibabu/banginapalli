/**
 * Question
 Given a m X n matrix. Print resultant matrix after k anticlock rotations
 Input: { { 1, 2 }, { 2, 3 }, { 4, 5 }, { 6, 7 } } , k=5
 Output:  {{2, 3, 5, 7},{1, 2, 4, 6 }}
 */


public class KthRotatedMatrix {

    public static Integer[][] rotateMatrix(Integer[][] A){
        Integer[][] r = new Integer[0][];
        if(A.length > 0) {
            r = new Integer[A[0].length][];
            for (Integer i = 0; i < A.length; i++) {
                for (Integer j = 0; j < A[i].length; j++) {
                    if(r[j] == null){
                        r[j] = new Integer[A.length];
                    }
                    r[j][i] = A[i][j];
                }
            }
        }
        return r;
    }

    public static void printMatrix(Integer[][] matrix){
        for(Integer i = 0; i < matrix.length; i++){
            for(Integer j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static Integer[][] process(Integer[][] matrix, Integer k){
        if(k % 2 == 1){
            return rotateMatrix(matrix);
        }else{
            return matrix;
        }
    }

    public static void main(String[] args){
        Integer[][] A = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        Integer[][] B = { { 10, 11 }, { 13, 14 }, { 16, 17 } };
        //printMatrix(rotateMatrix(A));
        //printMatrix(rotateMatrix(B));

        printMatrix(process(A, 3));
        printMatrix(process(A, 8));
        printMatrix(process(B, 4));
        printMatrix(process(B, 9));
    }

}
