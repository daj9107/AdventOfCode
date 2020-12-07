import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Rules {

	String color = "";

	static int containsCounter = 0;

	LinkedList<String> contents = new LinkedList<String>();
	
	LinkedList<Rules> containedBags = new LinkedList<Rules>();

	LinkedList<String> lines = new LinkedList<String>();

	static LinkedList<String> containsList = new LinkedList<String>();

	static LinkedList<String> notContainsList = new LinkedList<String>();

	static LinkedList<String> log = new LinkedList<String>();

	static LinkedList<String> loopTracker = new LinkedList<String>();

	Rules(String ruleDescription, LinkedList<String> lines){

		this.lines = lines;

		// finds color
		Scanner sc = new Scanner(ruleDescription);

		String tempWord = "";

		while(sc.hasNext()){
			tempWord = sc.next();
			if (tempWord.equals("bags")){
				break;
			}
			this.color += tempWord;
		}

		// the word "contain"
		sc.next();

		// finds contents
		int counter = 0;
		String contentsString = "";

		Set<String> bagVariants = new HashSet<String>();
		bagVariants.add("bag,");
		bagVariants.add("bag.");
		bagVariants.add("bags,");
		bagVariants.add("bags.");
		
		while(sc.hasNext()){
			if (sc.hasNextInt()){
				counter = sc.nextInt();
			}
			else {
				tempWord = sc.next();
				if (bagVariants.contains(tempWord)){
					for (int i = 0; i < counter; i++){
						this.contents.add(contentsString);
					}
					contentsString = "";
				}
				else {
					contentsString += tempWord;
				}
			}
			
		}
		sc.close();

	}

	
	public int mustContain(){

		this.fillContents(this.lines);

		for (Rules bag : this.containedBags){
			containsCounter++;
			bag.mustContain();
		}

		return containsCounter;
	}

	public boolean findContents(String targetColor){

		if (containsList.contains(this.color)){
			return true;
		}
		else if (notContainsList.contains(this.color)){
			return false;
		}

		boolean contained = false;

		this.fillContents(this.lines);
			
		for (Rules bag : this.containedBags){

			if (bag.color.equals(targetColor)){
				return true;
			}
			else {
				if (!bag.contents.isEmpty()){
					contained = bag.findContents(targetColor);
					if (contained){
						return true;
					}
				}
			}

		}

		if (contained){
			containsList.add(this.color);
		}
		else {
			notContainsList.add(this.color);
		}

		return contained;

	}

	public void fillContents(LinkedList<String> lines){

		for (String content : this.contents){
			String tempColor = "";
			String tempWord = "";
			for (String line: lines){
				Scanner sc2 = new Scanner(line);
				while(sc2.hasNext()){
					tempWord = sc2.next();
					if (tempWord.equals("bags")){
						break;
					}
					tempColor += tempWord;
				}
				if (tempColor.equals(content)){
					this.containedBags.add(new Rules(line, lines));
				}
				else {
					tempColor = "";
				}
				sc2.close();
			}
			
		}

	}

}