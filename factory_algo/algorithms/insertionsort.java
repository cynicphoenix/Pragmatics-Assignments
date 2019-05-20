//Java program for implementation of insertion sort
//Code taken from geeksforgeeks

package algorithms;
public class insertionsort extends SortingAlgorithm{
	
	public void print(){   
		 System.out.println("Insertion Sort in progress ...");
	}
	
	public int[] sort(int[] arr){
		int n = arr.length;
        int i, key, j;
		for (i = 1; i < n; i++){
			key = arr[i];
			j = i-1;
			//shift the elements
			while (j >= 0 && arr[j] > key){
				arr[j+1] = arr[j];
				j = j-1;
			}
			arr[j+1] = key;
		}
		return arr;
	}
}