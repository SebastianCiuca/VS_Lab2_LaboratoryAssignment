package controller;

import domain.Laboratory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.LaboratoryFileRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LaboratoryControllerTest {

    @BeforeClass
    public static void setup() throws IOException, ParseException {
        File f = new File("LaboratoryControllerTestFile.txt");
        File f2 = new File("LaboratoryControllerTestFilePassedStudentsEmpty.txt");
        File f3 = new File("LaboratoryControllerTestFilePassedStudents.txt");
        File f4 = new File("LaboratoryControllerIntegration.txt");
        File f5 = new File("StudentControllerIntegration.txt");

        PrintWriter pw = new PrintWriter(f.getName());
        PrintWriter pw2 = new PrintWriter(f2.getName());
        PrintWriter pw3 = new PrintWriter(f3.getName());
        PrintWriter pw4 = new PrintWriter(f4.getName());
        PrintWriter pw5 = new PrintWriter(f5.getName());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFilePassedStudents.txt")
        );
        Laboratory testLab = new Laboratory(1,"01/01/2019",2,"abcd1000");
        Laboratory testLab2 = new Laboratory(1,"01/01/2019",2,"abce1000");
        Laboratory testLab3 = new Laboratory(1,"01/01/2019",2,"abcg1000");
        Laboratory testLab4 = new Laboratory(1,"01/01/2019",2,"abcf1000");

        laboratoryController.saveLaboratory(testLab);
        laboratoryController.addGrade("abcd1000",1l,9);
        laboratoryController.saveLaboratory(testLab2);
        laboratoryController.addGrade("abce1000",1l,8);
        laboratoryController.saveLaboratory(testLab4);
        laboratoryController.addGrade("abcf1000",1l,9);
        laboratoryController.saveLaboratory(testLab3);
        laboratoryController.addGrade("abcg1000",1l,2);

    }

    @AfterClass
    public static void after() throws IOException, ParseException {
        File f = new File("LaboratoryControllerIntegration.txt");

        PrintWriter pw = new PrintWriter(f.getName());
    }

    /*
        Black box tests
     */

    @Test
    public void addGrade() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );

        Laboratory testLab = new Laboratory(1,"1/1/2019",2,"brie1000");
        laboratoryController.saveLaboratory(testLab);

        assertTrue(laboratoryController.addGrade("brie1000",1l,9));

        Laboratory testLab2 = new Laboratory(2,"1/2/2019",2,"brie1000");
        laboratoryController.saveLaboratory(testLab2);
        assertFalse(laboratoryController.addGrade("brie1000",2l,90));

    }

    @Test
    public void saveLaboratory() throws Exception {

        /*
            First use case - add a valid laboratory.
        */

        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );
        Laboratory testLab = new Laboratory(1,"01/01/2019",2,"bxie1000");
        assertTrue(laboratoryController.saveLaboratory(testLab));
        testLab.setLabNumber(-1);

        /*
            Second UC - attempting to save an invalid lab.
        */

        assertFalse(laboratoryController.saveLaboratory(testLab));
    }


    /*
        White box tests
     */

    @Test
    public void addGrade_valid_WBT() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );

        Laboratory newLab = new Laboratory(5,"1/1/2019",5,"amie1000");
        laboratoryController.saveLaboratory(newLab);

        assertTrue(laboratoryController.addGrade("amie1000",5l,9));
    }

    @Test
    public void addGrade_invalid_WBT() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );

        Laboratory newLab = new Laboratory(2,"1/1/2019",2,"mrie1000");
        laboratoryController.saveLaboratory(newLab);

        assertFalse(laboratoryController.addGrade("mrie1000",2l,-2));
    }

    @Test
    public void addGrade_invalidLab_WBT() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );

        Laboratory newLab = new Laboratory(5,"1/1/2019",5,"amie1000");
        laboratoryController.saveLaboratory(newLab);

        assertFalse(laboratoryController.addGrade("amie1000",-2l,9));
    }

    @Test
    public void saveLaboratory_valid_WBT() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );
        Laboratory testLab = new Laboratory(4,"01/01/2019",4,"alie1000");
        assertTrue(laboratoryController.saveLaboratory(testLab));
    }

    @Test
    public void saveLaboratory_invalid_WBT() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );
        Laboratory testLab = new Laboratory(1,"01/01/2019",-2,"arie1000");
        assertFalse(laboratoryController.saveLaboratory(testLab));
    }

    @Test
    public void saveLaboratory_unique_WBT() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );
        Laboratory testLab = new Laboratory(1,"01/01/2019",2,"nnie1000");
        assertTrue(laboratoryController.saveLaboratory(testLab));
    }

    @Test
    public void saveLaboratory_notUnique_WBT() throws Exception {
        File f = new File("LaboratoryControllerTestFile.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerTestFile.txt")
        );
        Laboratory testLab = new Laboratory(1,"01/01/2019",2,"abie1000");
        assertTrue(laboratoryController.saveLaboratory(testLab));
        assertFalse(laboratoryController.saveLaboratory(testLab));
    }

    @Test
    public void passedStudents_empty_WBT() throws Exception{
        File f = new File("LaboratoryControllerTestFilePassedStudentsEmpty.txt");
        assertTrue(f.exists());

        File f2 = new File("StudentControllerTestFilePassedStudents.txt");
        assertTrue(f2.exists());

        PrintWriter pw = new PrintWriter(f.getName());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository(f.getName())
        );

        assertTrue(laboratoryController.passedStudents(f2.getName()).isEmpty());
    }

    @Test
    public void passedStudents_content_WBT() throws Exception{
        File f = new File("LaboratoryControllerTestFilePassedStudents.txt");
        assertTrue(f.exists());

        File f2 = new File("StudentControllerTestFilePassedStudents.txt");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository(f.getName())
        );

        assertTrue(!laboratoryController.passedStudents(f2.getName()).isEmpty());
        assertTrue(laboratoryController.passedStudents(f2.getName()).size() == 3);
    }

    /*
        Integration
     */

    @Test
    public void saveLaboratory_integration() throws Exception {
        File f = new File("LaboratoryControllerIntegration");
        assertTrue(f.exists());
        PrintWriter pw = new PrintWriter(f.getName());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerIntegration")
        );

        Laboratory testLab = new Laboratory(5,"01/01/2019",2,"abcx1000");
        assertTrue(laboratoryController.saveLaboratory(testLab));
    }

    @Test
    public void addGrade_integration() throws Exception {
        File f = new File("LaboratoryControllerIntegration");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository("LaboratoryControllerIntegration")
        );

        assertTrue(laboratoryController.addGrade("abcx1000",5l,9));
    }

    @Test
    public void passedStudents_integration() throws Exception{
        File f = new File("LaboratoryControllerIntegration");
        assertTrue(f.exists());

        File f2 = new File("StudentControllerIntegration");
        assertTrue(f.exists());

        LaboratoryController laboratoryController = new LaboratoryController(
                new LaboratoryFileRepository(f.getName())
        );
        assertTrue(!laboratoryController.passedStudents(f2.getName()).isEmpty());
        assertTrue(laboratoryController.passedStudents(f2.getName()).size() == 1);

    }

    @Test
    public void integration() throws Exception{
        StudentControllerTest studentControllerTest = new StudentControllerTest();
        studentControllerTest.saveStudent_integration();
        saveLaboratory_integration();
        addGrade_integration();
        passedStudents_integration();
    }

}
