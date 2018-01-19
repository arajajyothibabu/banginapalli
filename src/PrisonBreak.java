import java.util.Arrays;
import java.util.HashSet;

public class PrisonBreak {

    private Integer n, m;
    private Integer[] x, y;

    public PrisonBreak(Integer n, Integer m, Integer[] x, Integer[] y) {
        this.n = n;
        this.m = m;
        this.x = x;
        this.y = y;
        this.compute();
    }

    private Long lengthOfLargestSubSequence(Integer[] arr){
        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.addAll(Arrays.asList(arr));
        Long length = 1L;
        for(Integer i : arr){
            if(!hashSet.contains(i - 1)){
                Integer j = i;
                while (hashSet.contains(j)){
                    j++;
                }
                if(length < j - i){
                    length = (long) (j - i);
                }
            }
        }
        return length;
    }

    private Long computeArea(){
        Long largestLength = lengthOfLargestSubSequence(this.x) + 1;
        Long largestBreadth = lengthOfLargestSubSequence(this.y) + 1;
        return largestLength * largestBreadth;
    }

    public void compute(){
        System.out.println(computeArea());
    }

}
