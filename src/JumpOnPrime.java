/**

 Ritik loves to jump here and there.So his friend Sugam gave him an array of N Integers in which he can choose any pair of Integers Ai and Aj only if i+K=j.Now he can add 1 to one of those elements and subtract one from the other i.e move 1 from one element to the other.He can perform this operation any number of times by jumping here and there in the array.
 Now Ritik wants to know if he can make all the numbers Prime by jumping and performing 0 or more number of given operations on the array.
 Note-0 and 1 are not considered as prime numbers.

 Input Format
 Your function contains two arguments A One Dimensional Integer Array of size N and an Integer K.
 First line of input contains an Integer K.(1<=K<=N)
 Second line of input contains an Integer N denoting the size of Array. (1<=N<=10000)
 Next N lines of input each containing an Integer Ai.(1<=i<=N , 1<=Ai<=100)

 Constraints
 1<=K<=N
 1<=N<=10000
 1<=i<=N , 1<=Ai<=100


 Output Format
 You must return 1 as answer if it possible to make all numbers as prime and -1 if not.
 Sample TestCase 1
 Input
 2
 5
 2
 8
 4
 5
 6
 Output
 1
 Explanation
 Move 1 from A3 to A5. Move 1 from A4 to A2. Move 1 from A4 to A2. Move 1 from A4 to A2. Final array {2,11,3,2,7}.Hence 1 since all are primes.

 */

import java.io.*;
import java.util.*;

public class JumpOnPrime {

    static Boolean isPrime(Integer n){
        if(n < 2) return false;
        Integer half = n / 2;
        for(Integer i = 2; i <= half; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    static Map<Integer, Boolean> primeMap(Integer N){
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for(Integer i = 0; i <= N; i++){
            map.put(i, isPrime(i));
        }
        return map;
    }

    static Map<Integer, Boolean> primesMap = primeMap(200);

    static Integer process(Integer K, Integer[] A){
        if(K < A.length){
            Integer[] sums = new Integer[K];
            Integer[] Ks = new Integer[K];
            Arrays.fill(sums, 0);
            Arrays.fill(Ks, 0);
            for(Integer i = 0; i < A.length; i++){
                sums[i % K] += A[i];
                Ks[i % K] += 1;
            }
            printArray(sums);
            printArray(Ks);
            for(Integer i= 0; i < K; i++){
                //System.out.println(isNumberSumOfKPrimes(sums[i], Ks[i]) + " - " + sums[i] + " - " + Ks[i]);
                if(!isNumberSumOfKPrimes(sums[i], Ks[i])){
                    return -1;
                }
            }
        }else{
            return -1;
        }
        return 1;
    }

    static void printArray(Integer[] A){
        for(Integer i = 0; i < A.length; i++){
            System.out.print(A[i] + ", ");
        }
        System.out.println();
    }

    static Boolean isNumberSumOfKPrimes(Integer N, Integer K){
        if(N == 2) return true;
        else if(N >= 2 * K && K == 2){
            if(N % 2 == 0) return true;
            else {
                return isPrime(N - 2);
            }
        }else /*(N >= 2 * K && K >= 3)*/{
            return true;
        }
    }

    public static void main(String args[] ) throws Exception {

        Scanner sc = new Scanner(System.in);
        //Integer K = sc.nextInt();
        //Integer N = sc.nextInt();
        //Integer[] A = new Integer[N];
        Integer[] A = {2, 8, 4, 3, 5, 6, 2, 4};
        /*for(Integer i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }*/
        System.out.println(process(2, A));
    }

}
