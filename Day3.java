/* --- Day 3: Toboggan Trajectory ---
 *
 * With the toboggan login problems resolved, you set off toward the
 * airport. While travel by toboggan might be easy, it's certainly not safe:
 * there's very minimal steering and the area is covered in trees. You'll
 * need to see which angles will take you near the fewest trees.
 *
 * Due to the local geology, trees in this area only grow on exact integer
 * coordinates in a grid. You make a map (your puzzle input) of the open
 * squares (.) and trees (#) you can see. For example:
 *
 * ..##.......
 * #...#...#..
 * .#....#..#.
 * ..#.#...#.#
 * .#...##..#.
 * ..#.##.....
 * .#.#.#....#
 * .#........#
 * #.##...#...
 * #...##....#
 * .#..#...#.#
 *
 * These aren't the only trees, though; due to something you read about once
 * involving arboreal genetics and biome stability, the same pattern repeats
 * to the right many times:
 *
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........#.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...##....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 *
 * You start on the open square (.) in the top-left corner and need to reach
 * the bottom (below the bottom-most row on your map).
 *
 * The toboggan can only follow a few specific slopes (you opted for a
 * cheaper model that prefers rational numbers); start by counting all the
 * trees you would encounter for the slope right 3, down 1:
 *
 * From your starting position at the top-left, check the position that is
 * right 3 and down 1. Then, check the position that is right 3 and down 1
 * from there, and so on until you go past the bottom of the map.
 *
 * The locations you'd check in the above example are marked here with O
 * where there was an open square and X where there was a tree:
 *
 * ..##.........##.........##.........##.........##.........##.......  --->
 * #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
 * .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
 * ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
 * .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
 * ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
 * .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
 * .#........#.#........X.#........#.#........#.#........#.#........#
 * #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
 * #...##....##...##....##...#X....##...##....##...##....##...##....#
 * .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
 *
 * In this example, traversing the map using this slope would cause you to
 * encounter 7 trees.
 * 
 * Starting at the top-left corner of your map and following a slope of
 * right 3 and down 1, how many trees would you encounter?
 *
 * --- Part Two ---
 *
 * Time to check the rest of the slopes - you need to minimize the
 * probability of a sudden arboreal stop, after all.
 *
 * Determine the number of trees you would encounter if, for each of the
 * following slopes, you start at the top-left corner and traverse the map
 * all the way to the bottom:
 *
 * Right 1, down 1.
 * Right 3, down 1. (This is the slope you already checked.)
 * Right 5, down 1.
 * Right 7, down 1.
 * Right 1, down 2.
 *
 * In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s)
 * respectively; multiplied together, these produce the answer 336.
 *
 * What do you get if you multiply together the number of trees encountered
 * on each of the listed slopes?
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3{
 	
	public static void main(String[] args) throws FileNotFoundException{

		String inputFile = "./Inputs/day_3_input.txt";
		String inputSample = "./Inputs/day_3_sample.txt";	

		int ans11 = tobogganPath(inputFile, 1, 1);
		int ans31 = tobogganPath(inputFile, 3, 1);
		int ans51 = tobogganPath(inputFile, 5, 1);
		int ans71 = tobogganPath(inputFile, 7, 1);
		int ans12 = tobogganPath(inputFile, 1, 2);
		System.out.println("Right 1, down 1: " + ans11);
		System.out.println("Right 3, down 1: " + ans31);
		System.out.println("Right 5, down 1: " + ans51);
		System.out.println("Right 7, down 1: " + ans71);
		System.out.println("Right 1, down 2: " + ans12);
		System.out.println("Product: " + (long)ans11 * (long)ans31 * (long)ans51 * (long)ans71 * (long)ans12);

	}

	public static int tobogganPath(String input, int rightX, int downY) throws FileNotFoundException{

		// Int to track the position from top left 0,0
		// Using Cartesian convention, ie x,y 
		int posX;
		int posY;

		// xMod will be used to wrap around
		int xMod;

		// Index 0 is rows and index 1 is cols
		int[] mapDimensions = lineCounter(input);
		int height = mapDimensions[0];
		int displacement = mapDimensions[1];

		// Counts trees encountered
		int trees = 0;

		// Grabs map
		String[] map = retrieveInput(input);

		// General purpose loop
		for (int i = 0; i < height / downY + height % downY; i++){

			xMod = (i * rightX / displacement) * displacement;
			posX = (i * rightX) - xMod;
			posY = i * downY;

			if (map[posY].charAt(posX) == '#'){
				trees++;
			}

		}

		return trees;

	}

	public static String[] retrieveInput(String input) throws FileNotFoundException{

		// Instantiates scanner object with input as arg
		File file = new File(input);
		Scanner sc = new Scanner(file);

		// Index 0 is rows and index 1 is cols
		int[] mapDimensions = lineCounter(input);

		// String array of input
		String[] map = new String[mapDimensions[0]];

		for (int i = 0; i < mapDimensions[0]; i++){
			map[i] = sc.nextLine();
		}

		return map;

	}

	public static int[] lineCounter(String input) throws FileNotFoundException{

		// Instantiates scanner objects with the day_2_input.txt as arg
		File file = new File(input);
		Scanner scCounter = new Scanner(file);

		// Counts length of the rows
		String firstLine = scCounter.nextLine();
		int colCounter = firstLine.length();

		// Counts number of lines in day_2_input.txt
		// Starts at one because one line has been used
		int rowCounter = 1;
		while (scCounter.hasNextLine()){
			scCounter.nextLine();
			rowCounter++;
		}
		scCounter.close();

		int[] counter = {rowCounter, colCounter};

		return counter;

	}

}