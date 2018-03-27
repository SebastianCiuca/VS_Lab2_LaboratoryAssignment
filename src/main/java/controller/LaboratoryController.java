package controller;

import domain.Laboratory;
import domain.Student;
import repository.LaboratoryFileRepository;
import repository.StudentFileRepository;
import validator.LaboratoryValidator;
import validator.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class LaboratoryController {
    private LaboratoryFileRepository laboratoryRepository = new LaboratoryFileRepository("laboratories.txt");
    private Validator<Laboratory> laboratoryValidator = new LaboratoryValidator();

    public LaboratoryController(LaboratoryFileRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    public boolean saveLaboratory(Laboratory laboratory) throws IOException, ParseException {
        boolean isSaved;
        if (laboratoryValidator.validate(laboratory)) {
            if(laboratoryRepository.findOne(laboratory))
                isSaved = false;
            else {
                this.laboratoryRepository.save(laboratory);
                isSaved = true;
            }
        } else {
            isSaved = false;
        }
        return isSaved;
    }

    public boolean addGrade(String student, Long labNumber, float grade)
            throws NumberFormatException, IOException, ParseException {
        boolean wasAdded;
        if (LaboratoryValidator.validateGrade(grade)) {
            wasAdded = this.laboratoryRepository.addGrade(student, labNumber, grade);
        } else {
            wasAdded = false;
        }

        return wasAdded;
    }

    /**
     * Returns the students that have passed their laboratories (mean grade >= 5)
     * @param studentFile - String representing the file name where the Student obj. are serialized
     * @return a list of Student Objects
     * @throws NumberFormatException
     * @throws IOException
     * @throws ParseException
     */
    public List<Student> passedStudents(String studentFile) throws NumberFormatException,
            IOException, ParseException {
        StudentFileRepository studentRepository = new StudentFileRepository(studentFile);

        Map<String, List<Laboratory>> laboratoryMap = laboratoryRepository.getLaboratoryMap();
        List<Student> studentsList = (List<Student>) studentRepository.findAll();

        List<Student> passedStudents = new ArrayList<Student>();
        Map.Entry<String, List<Laboratory>> entry;

        Set<Map.Entry<String, List<Laboratory>>> entrySet = laboratoryMap.entrySet();
        Iterator<Map.Entry<String, List<Laboratory>>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            entry = iterator.next();
            float midGrade = entry.getValue().get(0).getGrade();
            for (Laboratory laboratory : entry.getValue()) {
                midGrade = (midGrade + laboratory.getGrade()) / 2;
            }

            if (midGrade >= 5) {
                Student student = new Student();
                student.setRegNumber(entry.getKey());
                int indexOf = studentsList.indexOf(student);
                passedStudents.add(studentsList.get(indexOf));
            }
        }

        return passedStudents;
    }
}
