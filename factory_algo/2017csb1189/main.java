import algorithms.*;
import java.util.Scanner;


public class main {
    public static void main(String args[]) {
        //Scanner class for input from user
        Scanner sc = new Scanner(System.in);
        GetAlgorithmFactory newFactory = new GetAlgorithmFactory();
		while(true){
			System.out.println("--------------------------------------------");
        		System.out.println("Sort or Maths");
       			System.out.println("Enter 1: Sort");
        		System.out.println("Enter 2: Maths");
			System.out.println("--------------------------------------------");
			int init_choice = sc.nextInt();
        	if(init_choice == 1) {
				//Sorting algorithms
        		//Total length of array
        		System.out.print("Enter length of array : ");
        		int arrLength = sc.nextInt();
        		int arr[]=new int[arrLength];
		
        		//Initialize array
       		 	System.out.print("Enter elements of array : ");
        		for (int i = 0; i< arrLength; i++)
        			arr[i] = sc.nextInt();
				System.out.println("--------------------------------------------");
        		System.out.println("Which sort you want to perform ?");
        		System.out.println("Enter 1: Bubble Sort");
        		System.out.println("Enter 2: Insertion Sort");
        		System.out.println("Enter 3: Selection Sort");
        		System.out.println("Enter 4: Merge Sort");
        		System.out.println("Enter 5: Quick Sort");
        		int choice = sc.nextInt();
        		System.out.println("--------------------------------------------");
        		switch(choice) {
        			case 1:
        				SortingAlgorithm a = newFactory.getSortAlgo("bubble");
        				a.print();
        				a.sort(arr);
        			break;
        			case 2:
        				SortingAlgorithm b = newFactory.getSortAlgo("insertion");
        				b.print();
        				b.sort(arr);
        			break;
        			case 3:
        				SortingAlgorithm c = newFactory.getSortAlgo("selection");
        				c.print();
        				c.sort(arr);
        			break;
        			case 4:
        				SortingAlgorithm d = newFactory.getSortAlgo("merge");
        				d.print();
        				d.sort(arr);
        			break;
        			case 5:
        				SortingAlgorithm e = newFactory.getSortAlgo("quick");
        				e.print();
        				e.sort(arr);
        			break;
        			default :
        				System.out.println("Incorrect Choice");
        		}
			System.out.println("--------------------------------------------");
        		System.out.println("Sorted Array : ");
        		for (int i = 0; i < arrLength; i++)
        			System.out.println(+arr[i]);
       		 }
        	else if(init_choice == 2) {
			//Maths algorithms
				System.out.println("--------------------------------------------");
        		System.out.print("Enter two numbers : ");
        		int num1 = sc.nextInt();
        		int num2 = sc.nextInt();
				System.out.println("--------------------------------------------");
        		System.out.println("Which mathematical operation you want to perform ?");
        		System.out.println("Enter 1: GCD");
        		System.out.println("Enter 2: LCM");
        		System.out.println("Enter 3: Power");
        		int choice = sc.nextInt();
        		int result=0;
        		System.out.println("--------------------------------------------");
        		switch(choice) {
        			case 1:
        				MathAlgorithm a = newFactory.getMathAlgo("gcd");
        				a.print();
        				result = a.execute(num1, num2);
        			break;
        			case 2:
        				MathAlgorithm b = newFactory.getMathAlgo("lcm");
        				b.print();
        				result = b.execute(num1, num2);
        				break;
        			case 3:
        				MathAlgorithm c = newFactory.getMathAlgo("power");
        				c.print();
        				result = c.execute(num1, num2);
        			break;
        			default :
        				System.out.println("Incorrect Choice");
        		}
        		System.out.println("Result : "+result);
        	}
        	else
        		System.out.println("Incorrect Choice!");
			System.out.println("--------------------------------------------");
	
			//Continue Prompt
			System.out.println("To continue Press 1, else Press any key");
			int end_choice = sc.nextInt();
			if(end_choice != 1)
				break;
		}
	}
}

