package validator;

import domain.Laboratory;

import java.util.Date;

public class LaboratoryValidator implements Validator<Laboratory> {

    @Override
    public boolean validate(Laboratory laboratory) {
        if(laboratory.getLabNumber() < 1) {
            return false;
        }
        if(laboratory.getProblemNumber() > 10 || laboratory.getProblemNumber() < 1) {
            return false;
        }
        Date date = new Date();
        if(date.after(laboratory.getDate())) {
            return false;
        }

        return true;
    }

    public static boolean validateGrade(float grade) {
        if(grade >= 1 && grade <= 10) {
            return true;
        }
        return false;
    }
}
