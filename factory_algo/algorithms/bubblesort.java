//Java program for implementation of bubble sort
//Code taken from geeksforgeeks

package algorithms;
public class bubblesort extends SortingAlgorithm{
	
	public void print(){   
		 System.out.println("Bubble Sort in progress ...");
	}
	
    public int[] sort(int[] arr){
        int n = arr.length;
        
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (arr[j] > arr[j+1]){
                	//swap elements
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
			}
        }
    return arr;
    }
}