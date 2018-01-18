import java.util.ArrayList;
import java.util.Arrays;

public class SumPrimesSecondLargest {

    private String[] list;

    public SumPrimesSecondLargest() {

    }

    private boolean isPrime(Long n){
        if(n < 2 && n > -2) return false; //FIXME: no clarity
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private Long _compute(String sequence){
        list = sequence.split(" ");
        Long primes = 0L, firstMax = 0L, nextMax = 0L;
        for(String str : list){
            Long n = Long.parseLong(str);
            if(isPrime(n)){
                primes += 1;
                if(n > firstMax){
                    nextMax = firstMax;
                    firstMax = n;
                }else if(n > nextMax){
                    nextMax = n;
                }
            }
        }
        System.out.println(primes);
        System.out.println(nextMax);
        return nextMax + primes;
    }

    public void compute(String sequence){
        System.out.println(_compute(sequence));
    }

}
