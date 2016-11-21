public class TestLetterGrader {

	public static void main(String[] args) {
		//Check the correct amount of arguments are supplied
		if (args.length != 2) {
			printUsage();
			System.exit(1);
		}
		
		LetterGrader letterGrader = new LetterGrader(args[0], args[1]);
		
		try {
			letterGrader.readScore(); //Read the input file
			letterGrader.printGrade(); //Output each students letter grade into the output file
			letterGrader.displayAverages(); //Prints to the console the average, minimum, and maximum grades of each assignments
			letterGrader.doCleanup(); //Clean up resources			
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	//Display the program usage
	public static void printUsage() {
		System.out.println("Usage: java TestLetterGrader inputfile outputfile");
	}
}
