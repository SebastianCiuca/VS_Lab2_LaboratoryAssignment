package controller;

import domain.Laboratory;
import org.junit.Test;
import repository.LaboratoryFileRepository;

import java.io.File;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LaboratoryControllerTest {

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
        Laboratory testLab = new Laboratory(1,"01/01/2019",2,"brie1000");
        assertTrue(laboratoryController.saveLaboratory(testLab));
        testLab.setLabNumber(-1);

        /*
            Second UC - attempting to save an invalid lab.
        */

        assertFalse(laboratoryController.saveLaboratory(testLab));
    }
}
