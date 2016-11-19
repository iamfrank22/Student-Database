package studentDB;



public class StudentInfo {
	private String studentNumber;
	private String firstName;
	private char middleInitial;
	private String lastName;
	private String course;
	private int yearLevel;
	
	public StudentInfo() {
		
	}
	
	public StudentInfo(String studentNumber, String firstName, char middleInitial, 
			String lastName, String course, int yearLevel){
		
		this.studentNumber = studentNumber;
		
		this.firstName = firstName;
		
		this.middleInitial = middleInitial;
		
		this.lastName = lastName;
		
		this.course = course;
		
		this.yearLevel = yearLevel;
	}


	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNtumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public char getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(char middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getYearLevel() {
		return yearLevel;
	}
	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}
	
	
	public String toString(){
		return "Student Number: " + studentNumber 
				+ "\nFirst Name: " + firstName
				+ "\nMiddle Initial: " + middleInitial
				+ "\nLast Name: " + lastName
				+ "\nProgram: " + course
				+ "\nYear Level: " + yearLevel;
	}
	
}
