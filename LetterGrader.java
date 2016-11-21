import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class LetterGrader {
	private String inputFile = "";
	private String outputFile = "";
	private ArrayList<Student> students = new ArrayList<Student>(); //Storage area for all student object
	
	LetterGrader(String inputFile, String outputFile){
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}	
	
	public void readScore() throws Exception {
		try {
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader fileInStream = new BufferedReader(fileReader);
			
			//Initiate a student object and add the grades from the input file
			String record;
			//Go through each line in the text file
			while ((record = fileInStream.readLine()) != null) {
				//Parse the line by the comma
				String[] data = record.split(",");
				Student student = new Student(data[0]);
				for (int i = 1; i <= 7; i++) {
					student.addGrade(Integer.parseInt(data[i].trim()));
				}
				//Add the student to the storage
				students.add(student);
			}
			//Cleanup
			fileInStream.close();
			fileReader.close();
		}
		catch (FileNotFoundException e) {
			throw new Exception(String.format("File: %s not found", inputFile));
		}
		catch (IOException e) {
			throw new Exception(String.format("Error reading from file: %s", inputFile));
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public void printGrade() throws Exception {
		try {
			//Sort the students by name
			Collections.sort(students);
			
			//Initiate and output each students grade into the output file
			PrintWriter textPrintStream = null;
			textPrintStream = new PrintWriter(new FileOutputStream(outputFile));
			textPrintStream.printf("Letter grade for %s students given in %s file is:\r\n\r\n", students.size(), inputFile);
			//Loop through each student and get the grades
			for (int j = 0; j <= students.size()-1; j++) {
				//Get grades
				Student student = (Student)students.get(j);
				//Print and left align the students name then calculate and right align the letter grade
				textPrintStream.printf("%-25s%5s\r\n", String.format("%s:", student.getFullName()), calculateLetterGrade(student.getGrades()));
			}
			//Cleanup
			textPrintStream.close();
			textPrintStream = null;
		}
		catch (FileNotFoundException e) {
			throw new Exception(String.format("File: %s not found", inputFile));
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}

	//Calculate weighted letter grades
	private String calculateLetterGrade(ArrayList<Integer> grades) throws Exception {
		int finalScore = 0;
		
		try {
			//Final score weighted formula
			//Final Score = quiz1 * 0.10 + quiz2 * 0.10 + quiz3 * 0.10 + quiz4 * 0.10 + midI * 0.20 + midII * 0.15 + final * 0.25
			//Letter grade scale
			//A >= 90%, B = 80% - 89%, C = 70% - 79%, D = 60% - 69%, F <= 59%
			for (int k = 0; k <= grades.size()-1; k++) {
				if (k <= 3) //First 3 grades are 10%
					finalScore += (grades.get(k) * 0.10);
				if (k == 4) //First mid term is 20%
					finalScore += (grades.get(k) * 0.20);
				if (k == 5) //Second mid term is 15%
					finalScore += (grades.get(k) * 0.15);
				if (k == 6) //Final is 25%
					finalScore += (grades.get(k)* 0.25);
			}
		}
		catch (Exception e) {
			throw new Exception(e);			
		}
		
		//Return the letter grade
		if (finalScore >= 90)
			return "A";
		else if (finalScore >= 80 && finalScore <= 89)
			return "B";
		else if (finalScore >= 70 && finalScore <= 79)
			return "C";
		else if (finalScore >= 60 && finalScore <= 69)
			return "D";
		else if (finalScore <= 59)
			return "F";
		else //Return ? for unknown score
			return "?";
	}
	
	//Output to the console the assignments average, minimum, and maximum
	public void displayAverages() throws Exception{
		//Storage for each 7 assignments
		double[] averageGrade = {0,0,0,0,0,0,0};
		int[] minimumGrade = {100,100,100,100,100,100,100};
		int[] maximumGrade = {0,0,0,0,0,0,0};
		
		try {
			//Go through each student and get the grades
			for (int j = 0; j <= students.size()-1; j++) {
				Student student = students.get(j);
				ArrayList<Integer> grades = student.getGrades();
				for (int k = 0; k <= grades.size()-1; k++) {
					//Add up the grades
					averageGrade[k] = averageGrade[k] + grades.get(k);
					//Assign the minimum and maximum
					if (grades.get(k) < minimumGrade[k])
						minimumGrade[k] = grades.get(k);
					if (grades.get(k) > maximumGrade[k])
						maximumGrade[k] = grades.get(k);
				}
			}

			System.out.println("Here is the class averages:\n");
			System.out.println("\t\tQ1\tQ2\tQ3\tQ4\tMidI\tMidII\tFinal");
			
			//Print averages
			System.out.print("Average:");
			for(double d : averageGrade)
				System.out.printf("\t%3.2f", (d/students.size()));
			
			//Print minimum
			System.out.print("\nMinimum:");
			for(double d : minimumGrade)
				System.out.printf("\t%5.0f", d);
			
			//Print maximum
			System.out.print("\nMaximum:");
			for(double d : maximumGrade)
				System.out.printf("\t%5.0f", d);
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}

	//Cleanup resources section
	public void doCleanup(){
		students = null;
	}
}
