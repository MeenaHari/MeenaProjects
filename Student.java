import java.util.ArrayList;

public class Student extends Person implements Comparable<Student> {
	//Student grades storage
 	private ArrayList<Integer> grades = new ArrayList<Integer>();
 	
	public Student(String fullName) {
		super(fullName);
	}
	
	public void addGrade(int grade) {
		grades.add(grade);
	}
	//Get each grades
	public int getGrade(int grade){
		return grades.get(grade);
	}
	//Get all grades
	public ArrayList<Integer> getGrades() {
		return grades;
	}

	//Student compare class used for sorting
	@Override
	public int compareTo(Student o) {
		return super.getFullName().compareTo(o.getFullName());
	}
}
