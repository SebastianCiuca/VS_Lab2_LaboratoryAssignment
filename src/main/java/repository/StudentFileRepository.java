package repository;

import domain.Laboratory;
import domain.Student;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class StudentFileRepository implements Repository<Long, Student> {
    private String file;

    public StudentFileRepository(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void save(Student student) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(student.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Long id, Student item) {

    }

    @Override
    public Iterable findAll()
        throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<Student> allStudentsList = new ArrayList<Student>();

        String line = null;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Student student = new Student(temp[0], temp[1] + " " +  temp[2], Integer.valueOf(temp[3]));
            allStudentsList.add(student);
        }

        return allStudentsList;
    }

    public boolean findOne(Student student) throws IOException, ParseException {
        boolean found = true;

        for (Object l : findAll()){
            if (((Student) l).getRegNumber() == student.getRegNumber())
                return false;
        }

        return found;
    }
}
