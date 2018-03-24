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
        Student validS = new Student("bwie1000","Brie Wanaschow",933);
        Student invalidS1 = new Student("bwie10002","Brie Wanaschow",101);
        Student invalidS2 = new Student("bwie1000","Brie Wanaschow",1000);


        File f = new File("StudentControllerTestFile.txt");
        assertTrue(f.exists());

        StudentController studentController = new StudentController(
                new StudentFileRepository(f.getName())
        );

        assertTrue(studentController.saveStudent(validS));
        assertFalse(studentController.saveStudent(invalidS1));
        assertFalse(studentController.saveStudent(invalidS2));
    }

}