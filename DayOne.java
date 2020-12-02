// Part 1: From the input, find the two entries that sum to 2020 and return their product.
// Part 2: Find three entries that sum to 2020 and return their product.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayOne {
	
	public static void main(String[] args) throws FileNotFoundException{
		String inputFile = "./Inputs/dayOneInput.txt";
		System.out.println(findTwoThatSum2020(retrieveInput(inputFile)));
		System.out.println(findThreeThatSum2020(retrieveInput(inputFile)));
	}

	public static int[] retrieveInput(String input) throws FileNotFoundException{
		File file = new File(input);
		Scanner sc = new Scanner(file);
		Scanner sc2 = new Scanner(file);

		int counter = 0;

		while (sc.hasNextLine()){
			sc.nextLine();
			counter++;
		}
		sc.close();

		int[] inputArray = new int[counter];

		for (int i = 0; i < counter; i++){
			inputArray[i] = Integer.valueOf(sc2.nextLine());
		}
		sc2.close();

		return inputArray;
	}

	public static int findTwoThatSum2020(int[] input){
		int productOf = 0;		
		for (int i = 0; i < input.length; i++){
			for (int j = i+1; j < input.length; j++){
				if (input[i] + input[j] == 2020){
					productOf = input[i] * input[j];
				}
			}
		}
		return productOf;
	}

	public static int findThreeThatSum2020(int[] input){
		int productOf = 0;		
		for (int i = 0; i < input.length; i++){
			for (int j = i+1; j < input.length; j++){
				for (int k = j+1; k < input.length; k++){
					if (input[i] + input[j] + input[k] == 2020){
						productOf = input[i] * input[j] * input[k];
					}
				}
			}
		}
		return productOf;
	}

}
