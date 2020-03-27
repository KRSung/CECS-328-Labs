//  Kyle Sung 016917041
//  Lab 4
//  Returns the k numbers closest to the median

import java.util.*;

public class KElementsClosestToMedian {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int n, k;
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> diff = new ArrayList<>();
        HashMap<Integer, Integer> diffNegatives = new HashMap<>();
        System.out.print("Enter a positive integer: ");
        n = sc.nextInt();

        for(int i = 0; i < n; i++){
            a.add(rand.nextInt(100 + 100 + 1) - 100);
        }

        int medianIndex = a.size() / 2;

        System.out.println(a + "\n");

        System.out.print("Enter a number between 1 and " + n + ": ");
        k = sc.nextInt();
        sc.close();

        KElementsClosestToMedian quick = new KElementsClosestToMedian();

        int median = quick.Quick_select(a, 0, a.size() - 1, medianIndex);

        System.out.println("Median: " + median + "\n");

        for(int element:a){
            if (element - median < 0){
                if (diffNegatives.containsKey(element - median)){
                    diffNegatives.put((element - median) * -1, diffNegatives.get(element - median) + 1);
                }
                else{
                    diffNegatives.put((element - median) * -1, 1);
                }
            }
            diff.add(Math.abs(element - median));
        }

        quick.Quick_select(diff, 0, diff.size() - 1, k - 1);

        System.out.println("The " + k + " closest numbers to " + median + " are: ");
        diff.remove((Integer)0);
        int last = 0;
        for(int i = 0; i < k - 1 && i < diff.size() - 1; i++){
            if(diffNegatives.containsKey(diff.get(i))){
                diffNegatives.put(diff.get(i), diffNegatives.get(diff.get(i)) - 1);
                if(diffNegatives.get(diff.get(i)) == 0){
                    diffNegatives.remove(diff.get(i));
                }
                diff.set(i, diff.get(i) * -1);
            }
            if(diff.get(i) + median == median){
               k++;
               continue;
            }
            System.out.print(diff.get(i) + median + ", ");
            last++;
        }
        System.out.print(diff.get(last));
        System.out.println("\nThe runtime of this algorithm is O(n)");
    }

    public int Quick_select(ArrayList<Integer> arr, int left, int right, int k){
        KElementsClosestToMedian b = new KElementsClosestToMedian();
        int pivotIndex = b.partition(arr, left, right);
        if (k == pivotIndex){
            return arr.get(pivotIndex);
        }
        if (pivotIndex < k){
            return Quick_select(arr, pivotIndex + 1, right, k);
        }
        else{
            return Quick_select(arr, left, pivotIndex - 1, k);
        }
    }

    private int partition(ArrayList<Integer> arr, int left, int right){
        if (left == right){
            return left;
        }

        int pivot = arr.get(right);
        int rightWall = right;
        right--;

        while (right > left){
            if(arr.get(left) <= pivot){
                left++;
            }
            if (arr.get(right) >= pivot && right != left){
                right--;
            }
            if(arr.get(left) >= pivot && arr.get(right) <= pivot){
                swap(arr, left, right);
            }
        }
        if (arr.get(right) > pivot){
            swap(arr, rightWall, right);
        }
        else{
            swap(arr, ++right, rightWall);
        }
        return right;
    }

    private void swap(ArrayList<Integer> arr, int a, int b){
        int temp = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, temp);
    }
}