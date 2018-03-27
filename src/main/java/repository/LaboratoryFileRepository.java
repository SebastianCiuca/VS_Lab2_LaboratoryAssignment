package repository;

import domain.Laboratory;
import domain.Student;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaboratoryFileRepository implements Repository<Long, Laboratory> {
    private String file;

    public LaboratoryFileRepository(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void save(Laboratory laboratory) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(laboratory.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(Long id, Laboratory item) {

    }

    @Override
    public Iterable findAll()
            throws IOException, ParseException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Map<String, List<Laboratory>> laboratoryMap = new HashMap<String, List<Laboratory>>();

        List<Laboratory> allLabs = new ArrayList<>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Laboratory laboratory = new Laboratory(Integer.valueOf(temp[0]),
                    temp[1], Integer.valueOf(temp[2]), Float.valueOf(temp[3]),
                    temp[4]);
            allLabs.add(laboratory);
            /*if (laboratoryMap.get(laboratory.getStudentRegNumber()) == null) {
                List<Laboratory> laboratoryList = new ArrayList<Laboratory>();
                laboratoryList.add(laboratory);
                laboratoryMap.put(laboratory.getStudentRegNumber(),
                        laboratoryList);
            } else {
                laboratoryMap.get(laboratory.getStudentRegNumber()).add(
                        laboratory);
            }*/
        }
        return allLabs;//aici un lab nu are deja studentRegNb? De ce ii nevoie de mapare?
    }

    /**
     * Adds the given grade to the lab with the specified number and student regulation number.
     * @param studentRegNumber String signifying the student regulation number
     * @param labNumber Long number signifying the laboratory number
     * @param grade float value that represents the grade that will be attributed to the student for the given laboratory
     * @throws IOException
     * @throws NumberFormatException
     * @throws ParseException
     * @return boolean value representing whether the grade was added or not
     */
    public boolean addGrade(String studentRegNumber, Long labNumber, float grade)
        throws IOException, NumberFormatException, ParseException{
        File readFile = new File(file);
        File writeFile = new File("temp");

        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

        String line;

        boolean found = false;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            String fileLabNumber = temp[0];
            String fileStudentNumber = temp[4];

            if (fileLabNumber.equals(labNumber.toString()) && fileStudentNumber.equals(studentRegNumber)) {
                Laboratory laboratory = new Laboratory(
                        Integer.valueOf(temp[0]), temp[1],
                        Integer.valueOf(temp[2]), temp[4]);
                laboratory.setGrade(grade);
                writer.write(laboratory.toString() + "\n");
                found = true;
            } else {
                writer.write(line + "\n");
            }
        }
        writer.close();
        reader.close();

        readFile.delete();
        writeFile.renameTo(readFile);

        return found;
    }

    public boolean findOne(Laboratory laboratory) throws IOException, ParseException {
        boolean found = false;

        for (Object l : findAll()){
            if (((Laboratory) l).getStudentRegNumber().equals(laboratory.getStudentRegNumber()))
                return true;
        }

        return found;
    }

    /**
     * @return a map with the student registration number as key and laboratories of the student as values.
     * @throws NumberFormatException
     * @throws IOException
     * @throws ParseException
     */
    public Map<String, List<Laboratory>> getLaboratoryMap()
            throws NumberFormatException, IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        Map<String, List<Laboratory>> laboratoryMap = new HashMap<String, List<Laboratory>>();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(" ");
            Laboratory laboratory = new Laboratory(Integer.valueOf(temp[0]),
                    temp[1], Integer.valueOf(temp[2]), Float.valueOf(temp[3]),
                    temp[4]);
            if (laboratoryMap.get(laboratory.getStudentRegNumber()) == null) {
                List<Laboratory> laboratoryList = new ArrayList<Laboratory>();
                laboratoryList.add(laboratory);
                laboratoryMap.put(laboratory.getStudentRegNumber(),
                        laboratoryList);
            } else {
                laboratoryMap.get(laboratory.getStudentRegNumber()).add(
                        laboratory);
            }
        }
        return laboratoryMap;
    }
}
