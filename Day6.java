/*
 * --- Day 6: Custom Customs ---
 *
 * As your flight approaches the regional airport where you'll switch to a
 * much larger plane, customs declaration forms are distributed to the passengers.
 *
 * The form asks a series of 26 yes-or-no questions marked a through z. All
 * you need to do is identify the questions for which anyone in your group
 * answers "yes". Since your group is just you, this doesn't take very long.
 *
 * However, the person sitting next to you seems to be experiencing a
 * language barrier and asks if you can help. For each of the people in
 * their group, you write down the questions for which they answer "yes",
 * one per line. For example:
 *
 * abcx
 * abcy
 * abcz
 *
 * In this group, there are 6 questions to which anyone answered "yes": a, b,
 * c, x, y, and z. (Duplicate answers to the same question don't count extra;
 * each question counts at most once.)
 * 
 * Another group asks for your help, then another, and eventually you've
 * collected answers from every group on the plane (your puzzle input). Each
 * group's answers are separated by a blank line, and within each group,
 * each person's answers are on a single line. For example:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 *
 * This list represents answers from five groups:
 *
 * The first group contains one person who answered "yes" to 3 questions: a, b, and c.
 * The second group contains three people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The third group contains two people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The fourth group contains four people; combined, they answered "yes" to only 1 question, a.
 * The last group contains one person who answered "yes" to only 1 question, b.
 *
 * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
 * 
 * For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
 *
 * --- Part Two ---
 *
 * As you finish the last group's customs declaration, you notice that you
 * misread one word in the instructions:
 *
 * You don't need to identify the questions to which anyone answered "yes";
 * you need to identify the questions to which everyone answered "yes"!
 *
 * Using the same example as above:
 *
 * abc
 * 
 * a
 * b
 * c
 *
 * ab
 * ac
 * 
 * a
 * a
 * a
 * a
 * 
 * b
 *
 * This list represents answers from five groups:

 *  In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
 *  In the second group, there is no question to which everyone answered "yes".
 *  In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes" to b or c, they don't count.
 *  In the fourth group, everyone answered yes to only 1 question, a.
 *  In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.
 *
 * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
 *
 * For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?


*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6{

	public static void main(String[] args) throws FileNotFoundException{

		String inputSample = "./Inputs/day_6_sample.txt";
		String inputFile = "./Inputs/day_6_input.txt";

		File file = new File(inputFile);

		int ans = yesCounter(file);
		System.out.println(ans);

		int ans2 = notUnique(file);
		System.out.println(ans2);

	}

	public static int notUnique(File file) throws FileNotFoundException{

		Scanner sc = new Scanner(file);

		String tempLine;

		int yesCounter = 0;

		String notUniqueLetters = "";

		String tempNotUnique = "";

		boolean newGroup = true;

		for (int i = 0; i < lineCounter(file); i++){

			tempLine = sc.nextLine();

			if (!sc.hasNextLine()){
					yesCounter += notUniqueLetters.length();
			}

			else if (tempLine.isEmpty()){

				yesCounter += notUniqueLetters.length();
				notUniqueLetters = "";
				newGroup = true;

			}

			else if (newGroup){
				notUniqueLetters = tempLine;
				newGroup = false;

			}

			else {

				for (char letter: tempLine.toCharArray()){

					if (notUniqueLetters.indexOf(letter) > -1){
						tempNotUnique += letter;
					}

				}

				notUniqueLetters = tempNotUnique;
				tempNotUnique = "";

			}



		}

		sc.close();

		return yesCounter;
	}

	public static int yesCounter(File file) throws FileNotFoundException{

		Scanner sc = new Scanner(file);

		String tempLine;

		int yesCounter = 0;

		String uniqueLetters = "";

		for (int i = 0; i < lineCounter(file); i++){

			tempLine = sc.nextLine();

			if (tempLine.isEmpty()){

				uniqueLetters = "";

			}

			else {

				for (char letter: tempLine.toCharArray()){

					if (uniqueLetters.indexOf(letter) == -1){
						uniqueLetters += letter;
						yesCounter++;
					}

				}

			}

		}

		sc.close();

		return yesCounter;
	}



	public static int lineCounter(File file) throws FileNotFoundException{

		Scanner scCounter = new Scanner(file);

		int lineCounter = 0;
		while (scCounter.hasNextLine()){
			scCounter.nextLine();
			lineCounter++;
		}
		scCounter.close();

		return lineCounter;

	}


}