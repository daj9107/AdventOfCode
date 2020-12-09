

public class Instructions{

	int location;

	String command;

	int value;

	int counter;

	Instructions(int location, String command, int value){
		this.location = location;
		this.command = command;
		this.value = value;
		this.counter = 0;

	}

	public void swapCommand(){

		if (this.command.equals("nop")){
			this.command = "jmp";
		}
		else if (this.command.equals("jmp")){
			this.command = "nop";
		}

	}
}