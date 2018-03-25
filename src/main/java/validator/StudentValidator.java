package validator;

import domain.Student;

public class StudentValidator implements Validator<Student> {

    @Override
    public boolean validate(Student student) {
        if(!student.getRegNumber().matches("[a-zA-Z]{4}[\\d]{4}")){
            return false;
        }
        if (!student.getName().matches("[a-zA-Z]+[\\s][a-zA-Z]+")) {
            return false;
        }
        if(student.getGroup() >= 1000 || student.getGroup() <= 99){
            return false;
        }
        return true;
    }
}
