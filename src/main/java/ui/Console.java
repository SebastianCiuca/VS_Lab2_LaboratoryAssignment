package ui;

import controller.LaboratoryController;
import controller.StudentController;
import domain.Laboratory;
import domain.Student;
import repository.LaboratoryFileRepository;
import repository.StudentFileRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

public class Console {
    private LaboratoryController laboratoryController;
    private StudentController studentController;

    public void run() throws IOException{
        System.out.println("Starting");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        laboratoryController = new LaboratoryController(new LaboratoryFileRepository("laboratories.txt"));
        studentController = new StudentController(new StudentFileRepository("students.txt"));

        while(true){
            System.out.println("\n\n 1) Add student \n 2) Add Laboratory \n 3) Add grade \n 4) Get passing students \n");

            String line = br.readLine();

            switch (line.charAt(0)){
                case '1':
                    String registrationNumber, name;
                    int group;
                    System.out.print("\nStudent Registration number: ");
                    registrationNumber = br.readLine();
                    System.out.print("Name: ");
                    name = br.readLine();
                    try {
                        System.out.print("Group number: ");
                        String groupString = br.readLine();
                        group = Integer.parseInt(groupString);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid group - not a number \n");
                        continue;
                    }

                    Student student = new Student(registrationNumber, name, group);
                    Boolean success = null;
                    try {
                        success = studentController.saveStudent(student);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (!success) {
                        System.out.println("Invalid student, try again!\n");
                    }

                    break;

                case '2':
                    int number, problemNumber;
                    String date, studentRegNumber;

                    try {
                        System.out.println("\nLab number: ");
                        String labNumberString = br.readLine();
                        System.out.println("Problem number: ");
                        String labProblemNumberString = br.readLine();
                        number = Integer.parseInt(labNumberString);
                        problemNumber = Integer.parseInt(labProblemNumberString);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input\n");
                        continue;
                    }

                    System.out.println("Date (dd/mm/yyy)");
                    date = br.readLine();
                    System.out.println("Student reg number");
                    studentRegNumber = br.readLine();
                    Laboratory lab;
                    try {
                        lab = new Laboratory(number, date, problemNumber, studentRegNumber);
                    } catch (ParseException e) {
                        System.out.println("Invalid input\n");
                        continue;
                    }
                    success = false;
                    try {
                        success = laboratoryController.saveLaboratory(lab);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (!success) {
                        System.out.println("Invalid laboratory, please try again!\n");
                    }

                    break;

                case '3':
                    String labNumber;
                    float grade;
                    System.out.println("\nStudent Reg number: ");
                    registrationNumber = br.readLine();
                    System.out.println("Lab number: ");
                    labNumber = br.readLine();
                    try {
                        System.out.println("Grade: ");
                        String gradeString = br.readLine();
                        grade = Float.parseFloat(gradeString);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid grade\n");
                        continue;
                    }
                    try {
                        success = laboratoryController.addGrade(registrationNumber, Long.valueOf(labNumber), grade);
                        if (!success) {
                            System.out.println("Cannot save grade\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Cannot save grade\n");
                    }
                    catch (IOException e) {
                        System.out.println("Cannot save grade\n");
                    }
                    catch (ParseException e) {
                        System.out.println("Cannot save grade\n");
                    }

                    break;

                case '4':
                    try {
                        List<Student> passingStudents = laboratoryController.passedStudents("students.txt");
                        System.out.println("\nPassing students: ");
                        for ( Student s : passingStudents) {
                            System.out.println("\t - " + s.toString());
                        }

                        if (passingStudents.size() == 0)
                            System.out.println("No students are passing! Nice University we got here");

                    } catch (ParseException e) {
                        System.out.println("Could not get passing students\n");
                    }

                    break;

                default:
                    System.out.println("Invalid option! Please try again.\n");
            }
        }
    }

}
