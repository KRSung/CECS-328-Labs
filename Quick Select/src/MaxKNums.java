//  Kyle Sung 016917041
//  Lab 3 Part B

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MaxKNums {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int n, k;
        ArrayList<Integer> arr = new ArrayList<>();

        System.out.println("Enter a number: ");
        n = sc.nextInt();

        for(int i = 0; i < n; i++){
            arr.add(rand.nextInt(100 + 100 + 1) - 100);
        }

        System.out.println(arr + "\n");

        System.out.println("Enter the K max elements to find between 1 and " + n + ": ");
        k = sc.nextInt();
        sc.close();

        MaxKNums a = new MaxKNums();

        System.out.println("The top " + k + " numbers in the list are: \n"
                + a.Quick_select(arr, 0, arr.size() - 1, k - 1));
    }

    public ArrayList<Integer> Quick_select(ArrayList<Integer> arr, int left, int right, int k){
        MaxKNums b = new MaxKNums();
        int pivotIndex = b.partition(arr, left, right);
        int topK = arr.size() - k - 1;
        if (topK == pivotIndex){
            ArrayList<Integer> topKNums = new ArrayList<>();
            for(int i = topK; i < arr.size(); i++){
                topKNums.add(arr.get(i));
            }
            return topKNums;
        }
        if (pivotIndex < topK){
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
            if (arr.get(right) >= pivot){
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
