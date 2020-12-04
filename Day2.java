/* --- Day 2: Password Philosophy ---
 * Your flight departs in a few days from the coastal airport; the easiest
 * way down to the coast from here is via toboggan.
 * 
 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad
 * day. "Something's wrong with our computers; we can't log in!" You ask if
 * you can take a look.
 *
 * Their password database seems to be a little corrupted: some of the 
 * passwords wouldn't have been allowed by the Official Toboggan Corporate
 * Policy that was in effect when they were chosen.
 *
 * To try to debug the problem, they have created a list (your puzzle input)
 * of passwords (according to the corrupted database) and the corporate 
 * policy when that password was set.
 *
 * For example, suppose you have the following list:
 * 1-3 a: abcde
 * 1-3 b: cdefg
 * 2-9 c: ccccccccc
 *
 * Each line gives the password policy and then the password. The password
 * policy indicates the lowest and highest number of times a given letter
 * must appear for the password to be valid. For example, 1-3 a means that
 * the password must contain a at least 1 time and at most 3 times.
 *
 * In the above example, 2 passwords are valid. The middle password, cdefg,
 * is not; it contains no instances of b, but needs at least 1. The first
 * and third passwords are valid: they contain one a or nine c, both within
 * the limits of their respective policies.
 *
 *
 * How many passwords are valid according to their policies?
 *
 *--- Part Two ---
 *
 * While it appears you validated the passwords correctly, they don't seem
 * to be what the Official Toboggan Corporate Authentication System is
 * expecting.
 *
 * The shopkeeper suddenly realizes that he just accidentally explained the
 * password policy rules from his old job at the sled rental place down the
 * street! The Official Toboggan Corporate Policy actually works a little
 * differently.
 *
 * Each policy actually describes two positions in the password, where 1
 * means the first character, 2 means the second character, and so on. (Be
 * careful; Toboggan Corporate Policies have no concept of "index zero"!)
 * Exactly one of these positions must contain the given letter. Other
 * occurrences of the letter are irrelevant for the purposes of policy
 * enforcement.
 *
 * Given the same example list from above:
 * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
 * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
 * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
 *
 * How many passwords are valid according to the new interpretation of the policies?
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {

	public static void main(String[] args) throws FileNotFoundException{

		String inputFile = "./Inputs/day_2_input.txt";

		String[][] passwordData = retrieveInput(inputFile);

		int[] answer = validCheck(passwordData, inputFile);

		System.out.println("Part 1: " + answer[0]);
		System.out.println("Part 2: " + answer[1]);
	}

	public static int[] validCheck(String[][] passwordData, String input) throws FileNotFoundException{

		// Tracks how many valid passwords are tested under each ruleset
		int validCount = 0;
		int adjustedValidCount = 0;

		int lineCounter = lineCounter(input);

		// Iterates through every row
		for (int row = 0; row < lineCounter; row++){

			// Tracks character count
			int tempCount = 0;

			// Iterates through the password
			for (int i = 0; i < passwordData[row][3].length(); i++){

				// Checks each char in the password against the test char
				if (passwordData[row][2].charAt(0) == passwordData[row][3].charAt(i)){
					tempCount++;
				}
			}

			// Checks if the count matches the parameters
			if (tempCount >= Integer.valueOf(passwordData[row][0]) 
			&& tempCount <= Integer.valueOf(passwordData[row][1])){
				validCount++;
			}

			// Checks if the password matches the adjusted parameters
			if (passwordData[row][2].charAt(0) == passwordData[row][3].charAt(Integer.valueOf(passwordData[row][0])-1)
			^ passwordData[row][2].charAt(0) == passwordData[row][3].charAt(Integer.valueOf(passwordData[row][1])-1)){
				adjustedValidCount++;
			}

		}
		
		int[] answer = {validCount, adjustedValidCount};
		return answer;

	}

	public static String[][] retrieveInput(String input) throws FileNotFoundException{

		// Instantiates scanner objects with the day_2_input.txt as arg
		File file = new File(input);
		Scanner scStr = new Scanner(file);

		int lineCounter = lineCounter(input);

		// Converts file to string
		// The + " " is needed so the password doesn't run into the next lower limit.
		String strFile = new String();
		for (int i = 0; i < lineCounter; i++){
			strFile += scStr.nextLine() + " ";
		}
		scStr.close();

		// Replaces the "-" and ":" with a blank space so Scanner.next()
		// It will grab just the first number ie: 1 instead of 1-3
		strFile = strFile.replaceAll("-", " ");
		strFile = strFile.replaceAll(":", " ");

		// Instantiates scanner object with string output of file
		// after removing "-" and ":"
		Scanner scData = new Scanner(strFile);

		// creates String array with n=lineCounter rows and 4 columns
		// columns are lower/upper limits, letter parameter, and password
		String[][] passwordData = new String[lineCounter][4];

		// iterates through scanner object to fill String array data
		for (int row = 0; row < lineCounter; row++){
			for (int col = 0; col < 4; col++){
				passwordData[row][col] = scData.next();
			}
		}
		scData.close();

		return passwordData;

	}

	public static int lineCounter(String input) throws FileNotFoundException{

		// Instantiates scanner objects with the day_2_input.txt as arg
		File file = new File(input);
		Scanner scCounter = new Scanner(file);

		// Counts number of lines in day_2_input.txt
		int lineCounter = 0;
		while (scCounter.hasNextLine()){
			scCounter.nextLine();
			lineCounter++;
		}
		scCounter.close();

		return lineCounter;

	}

}