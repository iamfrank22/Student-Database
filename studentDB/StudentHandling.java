package studentDB;

import java.util.*;
import java.util.List;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class StudentHandling extends JPanel{
	private static List<StudentInfo> studList = new ArrayList<StudentInfo>();
	private static Scanner scanner = new Scanner(System.in);
	private JPanel buttons;
	private JButton register;
	private JButton search;
	private JButton delete;
	private JButton save;
	private JButton quit;
	private JTextField text;
	
	
	public StudentHandling(){
		BufferedReader br = null;
		String currentLine;
		int temp = 1;
		String studentNumber = "";
	    String firstName = "";
	    char middleInitial = ' ';
	    String lastName = "";
	    String course = "";
	    int yearLevel = 0;
	    
	    int choice;
	    boolean exit = false;
		
	    JFrame frame = new JFrame("Student Information System");
	    frame.setSize(400, 500);
	    frame.setVisible(true);
	    frame.getContentPane().setLayout(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
//		buttons = new JPanel();
//	    buttons.setLayout(new FlowLayout());
//	    buttons.setVisible(true);
//	    
//	    register = new JButton("Register");
//	    search = new JButton("Search");
//	    delete = new JButton("Delete");
//	    save = new JButton("Save");
//	    quit = new JButton("Exit");
//
//	    
//	    register.setBounds(10, 20, 89, 23);
//	    frame.getContentPane().add(register);
//	    
//	    search.setBounds(10, 70, 89, 23);
//	    frame.getContentPane().add(search);
//	    
//	    delete.setBounds(10, 120, 89, 23);
//	    frame.getContentPane().add(delete);
//		
//	    buttons.add(search);
//	    search.setBounds(100, 40, 40, 10);
//	    
//	    buttons.add(delete);
//	    delete.setBounds(20, 60, 40, 10);
//
//	    buttons.add(save);
//	    save.setBounds(20, 80, 40, 10);
	    
	    
		while(!exit){
			System.out.println("\nWhat do you want to do!??");
			System.out.println("1. Register");
			System.out.println("2. Search");
			System.out.println("3. Delete");
			System.out.println("4. Save");
			System.out.println("5. Exit ");
			System.out.println("Your Choice: ");
			
			choice = scanner.nextInt();
			
			if(choice < 1 || choice > 5){
				System.out.println("ERROR! Invalid input!");
				System.out.println("Please try again.\n\n");
			}
			
			if(choice == 1){
				register();
			} else
			if(choice == 2){
				search();
			} else
			if(choice == 3){
				delete();
			} else 
			if(choice == 4){
				save();
			} else{
				exit = true;
				System.exit(0);
			}
		}
		
		try{
			br = new BufferedReader(new FileReader("db.txt"));
			
			while((currentLine = br.readLine()) != null){
				if(temp == 1){
					studentNumber = currentLine;
				} else
				if(temp == 2){
					firstName = currentLine;
				} else
				if(temp == 3){
					if(currentLine.length() != 0)
						middleInitial = currentLine.charAt(0);
				} else
				if(temp == 4){
					lastName = currentLine;
				} else
				if(temp == 5){
					course = currentLine;
				} else{
					yearLevel = Integer.valueOf(currentLine);
					
					StudentInfo stud = new StudentInfo(studentNumber, firstName, 
							middleInitial, lastName, course, yearLevel);
					studList.add(stud);
					
					temp = 0;
		            studentNumber = "";
		            firstName = "";
		            middleInitial = ' ';
		            lastName = "";
		            course = "";
		            yearLevel = 0;
				}
				
				temp++;
			}
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			try{
				if(br != null)
					br.close();
			} catch(IOException ex){
				ex.printStackTrace();
			}
		}
		
		
		
		
		
		
		
	}
	
	public static boolean isStudNumExisting(String studentNumber){
		int temp = 0;
		StudentInfo curr = new StudentInfo();
		while(temp < studList.size()){
			if( ( (curr = studList.get(temp)).getStudentNumber() ).equals(studentNumber) )
				return true;
			
			temp++;
		}
		return false;
	}
	
	public void register(){
		scanner.nextLine();
		System.out.print("Student Number: ");
		String studentNumber = scanner.nextLine();
		
		
		while(isStudNumExisting(studentNumber) == true){
			System.out.println("Student Number ALREADY EXISTS!");
			System.out.println("Try again. ");
			studentNumber = scanner.nextLine();
		}
		
		System.out.print("First Name: ");
		String firstName = scanner.nextLine();
		
		System.out.print("Middle Initial: ");
		char middleInitial = scanner.next().charAt(0);
		
		scanner.nextLine();
		
		System.out.print("Last Name: ");
		String lastName = scanner.nextLine();
		
		System.out.print("Program: ");
		String course = scanner.nextLine();
		
		System.out.print("Year Level: ");
		int yearLevel = scanner.nextInt();
		
		StudentInfo student = new StudentInfo(studentNumber, firstName, 
							middleInitial, lastName, course, yearLevel);
		
		studList.add(student);
		
		System.out.println();
		System.out.println("Student added!");	
	}
	
	
	public void search(){
		System.out.print("Search Student (Enter student number): ");
		String studentNumber = scanner.next();
		StudentInfo retrievedStudent = null;
		
		while(isStudNumExisting(studentNumber) == false){
			System.out.println("Student Number DOES NOT EXIST!");
			System.out.println("Try again. ");
			studentNumber = scanner.next();
		}

		for(StudentInfo student : studList) {
			if(student.getStudentNumber().equals(studentNumber)) {
				retrievedStudent = student;
				break;
			}
		}
		System.out.println(retrievedStudent);
	}
	
	
	public void delete(){
		System.out.print("Delete Student (Enter student number): ");
		String studentNumber = scanner.next();
		
		while(isStudNumExisting(studentNumber) == false){
			System.out.println("Student Number DOES NOT EXIST!");
			System.out.println("Try again. ");
			studentNumber = scanner.next();
		}
		
		for(StudentInfo student : studList) {
			if(student.getStudentNumber().equals(studentNumber)) {
				studList.remove(student);
				break;
			}
		}

		System.out.println("Student " + studentNumber + " deleted!");
	}
	
	
	public void save() {
		BufferedWriter br = null;
		try {
			
			File file = new File("db.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
			
			br = new BufferedWriter(new FileWriter(file));
			for(StudentInfo student : studList) {
				br.write(student.getStudentNumber());
				br.newLine();

				br.write(student.getFirstName());
				br.newLine();

				br.write(student.getMiddleInitial());
				br.newLine();

				br.write(student.getLastName());
				br.newLine();

				br.write(student.getCourse());
				br.newLine();

				br.write(String.valueOf(student.getYearLevel()));
				br.newLine();
			}
		} catch (IOException ex) {
				ex.printStackTrace();
		} finally {
			try {
				if(br != null) br.close();
			} catch (IOException ex) {
					ex.printStackTrace();
			}
		}
		System.out.println("Student's info saved.");
	}
	
	
	
	public static void main(String[] args) {
		StudentHandling sis = new StudentHandling();
	    
}
}
