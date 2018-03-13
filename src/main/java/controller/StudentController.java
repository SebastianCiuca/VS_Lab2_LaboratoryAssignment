package controller;

import domain.Student;
import repository.Repository;
import repository.StudentFileRepository;
import validator.StudentValidator;
import validator.Validator;

import java.io.IOException;
import java.text.ParseException;

public class StudentController {
    private StudentFileRepository studentRepository = new StudentFileRepository("students.txt");
    private Validator<Student> studentValidator = new StudentValidator();

    public StudentController(StudentFileRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean saveStudent(Student student) throws IOException, ParseException {
        if (studentValidator.validate(student)) {
            if(studentRepository.findOne(student))
                return false;

            this.studentRepository.save(student);
            return true;
        } else {
            return false;
        }
    }
}
