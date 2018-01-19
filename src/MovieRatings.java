import java.util.ArrayList;

public class MovieRatings {

    public MovieRatings() {

    }

    /**
     *
     * @param ratings
     */
    public void compute(Integer[] ratings){
        Integer sum = ratings[0];
        Boolean left = false;
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for(int i = 1; i < ratings.length; i++){
            if(left){
                if(sum + ratings[i - 1] > sum + ratings[i]){
                    sum += ratings[i - 1];
                    indices.add(i - 1);
                }else{
                    sum += ratings[i];
                    indices.add(i);
                }
                left = false;
            }else if(sum + ratings[i] > sum){
                sum += ratings[i];
                indices.add(i);
                left = false;
            }else{
                left = true;
            }
        }

        System.out.println(sum);
    }

}
