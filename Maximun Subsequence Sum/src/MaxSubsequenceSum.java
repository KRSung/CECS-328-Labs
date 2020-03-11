import java.util.*;

public class MaxSubsequenceSum {
    public static void main(String[] args) {

    }

    public int mss(ArrayList<Integer> arr, int min, int max){
        ArrayList<Integer> Left = new ArrayList<>();
        ArrayList<Integer> Right = new ArrayList<>();

        if(arr.size() == 1){
            return arr.get(0);
        }

        int middle = (min + max) / 2;
        for(int i = 0; i <= middle; i++){
            Left.add(arr.get(i));
        }
        for(int i = middle + 1; i < arr.size(); i++){
            Right.add(arr.get(i));
        }




        return max(mss(Left, 0, middle), mss(Right, middle + 1, arr.size()-1));



    }

    public int max(int a, int b){

    }
}
