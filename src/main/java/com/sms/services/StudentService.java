package com.sms.services;

import com.sms.enums.Department;
import com.sms.exceptions.DuplicateStudentException;
import com.sms.exceptions.StudentNotFoundException;
import com.sms.models.Student;
import com.sms.storage.FileHandler;
import com.sms.utils.Sorter;

import java.util.*;

public class StudentService{

    private Map<String, Student> students = new HashMap<>();
    private FileHandler fileHandler = new FileHandler();
    private Sorter<Student> sorter = new Sorter<>();
    private int counter = 1000;

    StudentService(){
        List<Student> students = fileHandler.loadAll();
        for(Student st: students){
            this.students.put("STU"+(counter++), st);
        }
    }

    public String addStudent(String name, int age, double marks, Department.dept dept){
        String studentID = "S" + (counter++);
        if(students.containsKey(studentID))
            throw new DuplicateStudentException("Student already exists with StudentID: " + studentID);
        else{
            students.put(studentID, new Student(studentID, name, age, marks, dept));
        }
        save();
        return studentID;
    }

    public void updateStudent(String id, String newName, double newMarks) throws StudentNotFoundException{
        try{
            if(students.containsKey(id)) {
                Student st = students.get(id);
                st.setName(newName);
                st.setMarks(newMarks);
            }
        }catch (Exception e) {
            throw new StudentNotFoundException("Student does not exist with the given StudentID: " + id);
        }finally{
            save();
        }
    }

    public void deleteStudent(String id) throws StudentNotFoundException{
        try {
            if (students.containsKey(id)) {
                students.remove(id);
            }
        }catch (Exception e) {
            throw new StudentNotFoundException("Student does not exist with the given StudentID: " + id);
        }finally{
            save();
        }
    }

    public List<Student> searchByName(String name){
        List<Student> result = new ArrayList<>();

        for(Student student : students.values()){
            if((student.getName()).equalsIgnoreCase(name))
                result.add(student);
        }

        return result;
    }

    public List<Student> searchByDepartment(Department.dept dept){
        List<Student> result = new ArrayList<>();

        for(Student student : students.values()){
            if(student.getDepartment() == dept)
                result.add(student);
        }

        return result;
    }

    public List<Student> getAllSortedByName(){
        List<Student> result = new ArrayList<>(students.values());
        sorter.sort(result, Comparator.comparing(Student::getName));

        return result;
    }

    public List<Student> getAllSortedByMarks(){
        List<Student> result = new ArrayList<>(students.values());
        sorter.sort(result, Comparator.comparing(Student::getMarks));

        return result;
    }

    private void save(){
        List<Student> result = new ArrayList<>(students.values());
        fileHandler.saveAll(result);
    }
}