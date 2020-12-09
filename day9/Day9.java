/*
--- Day 9: Encoding Error ---

With your neighbor happily enjoying their video game, you turn your attention to an open data port on the little screen in the seat in front of you.

Though the port is non-standard, you manage to connect it to your computer through the clever use of several paperclips.
Upon connection, the port outputs a series of numbers (your puzzle input).

The data appears to be encrypted with the eXchange-Masking Addition System (XMAS) which, conveniently for you, is an old cypher with an important weakness.

XMAS starts by transmitting a preamble of 25 numbers. After that, each number you receive should be the sum of any two of the 25 immediately previous numbers. 
The two numbers will have different values, and there might be more than one such pair.

For example, suppose your preamble consists of the numbers 1 through 25 in a random order. 
To be valid, the next number must be the sum of two of those numbers:

    26 would be a valid next number, as it could be 1 plus 25 (or many other pairs, like 2 and 24).
    49 would be a valid next number, as it is the sum of 24 and 25.
    100 would not be valid; no two of the previous 25 numbers sum to 100.
    50 would also not be valid; although 25 appears in the previous 25 numbers, the two numbers in the pair must be different.

Suppose the 26th number is 45, and the first number (no longer an option, as it is more than 25 numbers ago) was 20. 
Now, for the next number to be valid, there needs to be some pair of numbers among 1-19, 21-25, or 45 that add up to it:

    26 would still be a valid next number, as 1 and 25 are still within the previous 25 numbers.
    65 would not be valid, as no two of the available numbers sum to it.
    64 and 66 would both be valid, as they are the result of 19+45 and 21+45 respectively.

Here is a larger example which only considers the previous 5 numbers (and has a preamble of length 5):

35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576

In this example, after the 5-number preamble, almost every number is the sum of two of the previous 5 numbers; the only number that does not follow this rule is 127.

The first step of attacking the weakness in the XMAS data is to find the first number in the list (after the preamble) which is not the sum of two of the 25 numbers before it. 
What is the first number that does not have this property?

--- Part Two ---

The final step in breaking the XMAS encryption relies on the invalid number you just found: 
you must find a contiguous set of at least two numbers in your list which sum to the invalid number from step 1.

Again consider the above example:

35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576

In this list, adding up all of the numbers from 15 through 40 produces the invalid number from step 1, 127. (Of course, the contiguous set of numbers in your actual list might be much longer.)

To find the encryption weakness, add together the smallest and largest number in this contiguous range; in this example, these are 15 and 47, producing 62.

What is the encryption weakness in your XMAS-encrypted list of numbers?
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;

public class Day9{

	public static void main(String[] args){

		System.out.println("Part one: " + findNot(25));

		System.out.println("Part two: " + findContigious(25));

	}

	public static int processContigious(int[] ans, LinkedList<Integer> inputList){

		int largest = inputList.get(ans[1]);
		int smallest = inputList.get(ans[1]);

		for (int i = 1; i < ans[0]; i++){
			if (inputList.get(ans[1] + i) > largest){
				largest = inputList.get(ans[1] + i);
			}
			if (inputList.get(ans[1] + i) < smallest){
				smallest = inputList.get(ans[1] + i);
			}
		}

		int weakness = smallest + largest;

		return weakness;

	}

	public static int findContigious(int param){

		LinkedList<Integer> inputList = retrieveInput();

		int targetNum = findNot(param);

		int sum = 0;

		int[] ans = new int[2];

		while (sum != targetNum){
			for (int i = 2; i < inputList.size() - i; i++){
				for (int j = 0; j < inputList.size() - i; j++){
					sum = 0;
					for (int k = 0; k < i; k++){
						sum += inputList.get(j+k);
						if (sum == targetNum && k == i-1){
							ans[0] = i;
							ans[1] = j;
							return processContigious(ans, inputList);
						}
					}				
				}
			}
		}

		return -1;

	}

	public static int findNot(int param){

		LinkedList<Integer> inputList = retrieveInput();

		// Starts with param due to 0 indexing, ie sixth entry in sample and 26th in main file
		for (int i = param; i < inputList.size(); i++){
			if (!doesSum(inputList, i, param)){
				return inputList.get(i);
			}
		}

		return -1;

	}

	public static boolean doesSum(LinkedList<Integer> inputList, int testNum, int param){

		// Starts with 0-23, and adds with 1-24, preventing duplicates
		// Returns true when it finds the testNum that doesn't sum two previous..
		// within the param range
		for (int i = testNum - param; i < testNum - 1; i++){
			for (int j = testNum - param + 1; j < testNum; j++){
				if (inputList.get(i) + inputList.get(j) == inputList.get(testNum)){
					return true;
				}
			}
		}

		return false;
		
	}

	public static LinkedList<Integer> retrieveInput(){
		
		String sampleFile = "day_9_sample.txt";
		String inputFile = "day_9_input.txt";

		LinkedList<Integer> inputList = new LinkedList<Integer>();

		try {
			File file = new File(inputFile);
			Scanner sc = new Scanner(file);

			while (sc.hasNextInt()){
				inputList.add(sc.nextInt());
			}
			sc.close();			
		} catch (FileNotFoundException e){
			System.out.println("File address is incorrect");
		}
	
		return inputList;

	}
}