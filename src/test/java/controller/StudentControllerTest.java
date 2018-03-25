package controller;

import domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.StudentFileRepository;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class StudentControllerTest {

    @Before
    public void setup(){

    }

    @After
    public void teardown(){

    }

    @Test
    public void saveStudent() throws IOException, ParseException {
        Student validS1 = new Student("bwie1000","Brie Wanaschow",933);
        Student validS2 = new Student("bwie1000","Brie Wanaschow",999);
        Student validS3 = new Student("bwie1000","Brie Wanaschow",100);
        Student invalidS1 = new Student("bwie10002","Brie Wanaschow",101);
        Student invalidS2 = new Student("bwie1000","Brie Wanaschow",1000);
        Student invalidS3 = new Student("bwie1000","Brie Wanaschow",10);
        Student invalidS4 = new Student("bw1000","Brie Wanaschow",109);
        Student invalidS5 = new Student("bwiee1000","Brie Wanaschow",102);
        Student invalidS6 = new Student("bwie99","Brie Wanaschow",101);


        File f = new File("StudentControllerTestFile.txt");
        assertTrue(f.exists());

        StudentController studentController = new StudentController(
                new StudentFileRepository(f.getName())
        );

        assertTrue(studentController.saveStudent(validS1));
        assertTrue(studentController.saveStudent(validS2));
        assertTrue(studentController.saveStudent(validS3));
        assertFalse(studentController.saveStudent(invalidS1));
        assertFalse(studentController.saveStudent(invalidS2));
        assertFalse(studentController.saveStudent(invalidS2));
        assertFalse(studentController.saveStudent(invalidS3));
        assertFalse(studentController.saveStudent(invalidS4));
        assertFalse(studentController.saveStudent(invalidS5));
        assertFalse(studentController.saveStudent(invalidS6));
    }

}