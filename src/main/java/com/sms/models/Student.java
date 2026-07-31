package com.sms.models;

import com.sms.enums.Department;

public class Student{
    private String studentId;
    private String name;
    private int age;
    private double marks;
    private Department.dept department;

    Student(String studentId, String name, int age, double marks, Department.dept department){
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.department = department;
    }

    String getStudentId(){
        return this.studentId;
    }

    String getName(){
        return this.name;
    }

    int getAge(){
        return this.age;
    }

    double getMarks(){
        return this.marks;
    }

    Department.dept getDepartment(){
        return this.department;
    }

    void setName(String name){
        this.name = name;
    }

    void setMarks(double marks){
        this.marks = marks;
    }

    String toCSV(){
        return String.join(",", studentId, name, String.valueOf(age), String.valueOf(marks), department.getDisplayName());
    }

    static Student fromCSV(String line){
        String[] parts = line.split(",");
        return new Student(
                parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), Department.dept.valueOf(parts[4])
        );
    }

    @Override
    public String toString(){
        return "[" + studentId + "] " + name + " | Age:" + age + " | Marks:" + marks + " | Dept: " +  department.getDisplayName();
    }
}