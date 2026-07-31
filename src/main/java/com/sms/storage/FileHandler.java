package com.sms.storage;

import com.sms.models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.sms.models.Student.fromCSV;

public class FileHandler {
    private static final String FILE_PATH = "students.txt";

    public void saveAll(List<Student> students){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Student student: students){
                bw.write(student.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> loadAll(){
        List<Student> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}