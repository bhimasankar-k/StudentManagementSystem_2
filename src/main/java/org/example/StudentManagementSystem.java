package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String department;
    private double marks;
    public Student(int id, String name, int age, String department, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}

public class StudentManagementSystem {

    static ArrayList<Student> studentList = new ArrayList<>();
    static HashMap<Integer, Student> studentMap = new HashMap<>();
    static LinkedHashMap<Integer, Student> linkedStudentMap = new LinkedHashMap<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // sample data so that options 6 to 10 can be tested quickly
        // (remove this line if you want to start with an empty list)
        loadSampleData();

        int choice = 0;

        do {
            System.out.println();
            System.out.println("======= STUDENT MANAGEMENT SYSTEM =======");
            System.out.println("1.  Add Student");
            System.out.println("2.  Display All Students");
            System.out.println("3.  Search Student");
            System.out.println("4.  Update Student");
            System.out.println("5.  Remove Student");
            System.out.println("6.  Display Students by Department");
            System.out.println("7.  Display Top Student");
            System.out.println("8.  Display Student Map (HashMap)");
            System.out.println("9.  Search Student Using Map");
            System.out.println("10. Display Students Using LinkedHashMap");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    displayByDepartment();
                    break;
                case 7:
                    displayTopStudent();
                    break;
                case 8:
                    displayStudentMap();
                    break;
                case 9:
                    searchUsingMap();
                    break;
                case 10:
                    displayLinkedHashMap();
                    break;
                case 11:
                    System.out.println("Exiting program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 11);

        sc.close();
    }

    // ---------- helper methods ----------

    static void loadSampleData() {
        saveStudent(new Student(101, "Ravi", 20, "CSE", 85.5));
        saveStudent(new Student(102, "Priya", 21, "ECE", 91.0));
        saveStudent(new Student(103, "Arun", 20, "IT", 78.5));
        saveStudent(new Student(104, "Sneha", 21, "CSE", 88.0));
        saveStudent(new Student(105, "Kiran", 22, "CSE", 95.0));
    }

    // adds the student to the list and both maps
    static void saveStudent(Student s) {
        studentList.add(s);
        studentMap.put(s.getId(), s);
        linkedStudentMap.put(s.getId(), s);
    }

    // returns the student with the given id, or null if not found
    static Student findStudent(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // ---------- 1. Add Student ----------
    static void addStudent() {
        int id;
        int age;
        double marks;

        System.out.print("Enter Student ID: ");
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
            return;
        }
        if (id <= 0) {
            System.out.println("Invalid Student ID.");
            return;
        }
        if (findStudent(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Age: ");
        try {
            age = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Age.");
            return;
        }
        if (age <= 0 || age > 100) {
            System.out.println("Invalid Age.");
            return;
        }

        System.out.print("Enter Department: ");
        String department = sc.nextLine().trim();

        System.out.print("Enter Marks: ");
        try {
            marks = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Marks should be between 0 and 100.");
            return;
        }
        if (marks < 0 || marks > 100) {
            System.out.println("Marks should be between 0 and 100.");
            return;
        }

        saveStudent(new Student(id, name, age, department, marks));
        System.out.println("Student added successfully.");
    }

    // ---------- 2. Display All Students ----------
    static void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("%-10s%-14s%-8s%-16s%-8s%n", "ID", "Name", "Age", "Department", "Marks");
        System.out.println("------------------------------------------------------------");
        for (Student s : studentList) {
            System.out.printf("%-10d%-14s%-8d%-16s%-8.1f%n",
                    s.getId(), s.getName(), s.getAge(), s.getDepartment(), s.getMarks());
        }
        System.out.println("------------------------------------------------------------");
    }

    // ---------- 3. Search Student ----------
    static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
            return;
        }

        Student s = findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student Found");
            System.out.println();
            System.out.println("ID          : " + s.getId());
            System.out.println("Name        : " + s.getName());
            System.out.println("Age         : " + s.getAge());
            System.out.println("Department  : " + s.getDepartment());
            System.out.println("Marks       : " + s.getMarks());
        }
    }

    // ---------- 4. Update Student ----------
    static void updateStudent() {
        System.out.print("Enter Student ID: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
            return;
        }

        Student s = findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String newName = sc.nextLine().trim();
        if (newName.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        int newAge;
        System.out.print("Enter New Age: ");
        try {
            newAge = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Age.");
            return;
        }
        if (newAge <= 0 || newAge > 100) {
            System.out.println("Invalid Age.");
            return;
        }

        System.out.print("Enter New Department: ");
        String newDept = sc.nextLine().trim();

        double newMarks;
        System.out.print("Enter New Marks: ");
        try {
            newMarks = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Marks should be between 0 and 100.");
            return;
        }
        if (newMarks < 0 || newMarks > 100) {
            System.out.println("Marks should be between 0 and 100.");
            return;
        }

        // the list and both maps hold the same object, so all of them get updated
        s.setName(newName);
        s.setAge(newAge);
        s.setDepartment(newDept);
        s.setMarks(newMarks);
        System.out.println("Student updated successfully.");
    }

    // ---------- 5. Remove Student ----------
    static void removeStudent() {
        System.out.print("Enter Student ID: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
            return;
        }

        Student s = findStudent(id);
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            studentList.remove(s);
            studentMap.remove(id);
            linkedStudentMap.remove(id);
            System.out.println("Student removed successfully.");
        }
    }

    // ---------- 6. Display Students by Department ----------
    static void displayByDepartment() {
        System.out.print("Enter Department: ");
        String dept = sc.nextLine().trim();

        System.out.println("--------- " + dept.toUpperCase() + " STUDENTS ---------");
        System.out.println();

        boolean found = false;
        for (Student s : studentList) {
            if (s.getDepartment().equalsIgnoreCase(dept)) {
                System.out.printf("%-6d%-10s%-8s%-6.1f%n",
                        s.getId(), s.getName(), s.getDepartment(), s.getMarks());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found in this department.");
        }
    }

    // ---------- 7. Display Top Student ----------
    static void displayTopStudent() {
        if (studentList.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        Student top = studentList.get(0);
        for (Student s : studentList) {
            if (s.getMarks() > top.getMarks()) {
                top = s;
            }
        }

        System.out.println("=====================================");
        System.out.println("            TOP STUDENT");
        System.out.println("=====================================");
        System.out.println();
        System.out.println("Student ID : " + top.getId());
        System.out.println("Name       : " + top.getName());
        System.out.println("Department : " + top.getDepartment());
        System.out.println("Marks      : " + top.getMarks());
        System.out.println();
        System.out.println("=====================================");
    }

    // ---------- 8. Display Student Map (HashMap) ----------
    static void displayStudentMap() {
        System.out.println("=====================================");
        System.out.println("            STUDENT MAP");
        System.out.println("=====================================");
        System.out.println();

        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().getName());
        }
    }

    // ---------- 9. Search Student Using Map ----------
    static void searchUsingMap() {
        System.out.print("Enter Student ID: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID.");
            return;
        }

        if (studentMap.containsKey(id)) {
            Student s = studentMap.get(id);
            System.out.println("Student Found");
            System.out.println();
            System.out.println("ID         : " + s.getId());
            System.out.println("Name       : " + s.getName());
            System.out.println("Department : " + s.getDepartment());
            System.out.println("Marks      : " + s.getMarks());
        } else {
            System.out.println("Student not found.");
        }
    }

    // ---------- 10. Display Students Using LinkedHashMap ----------
    static void displayLinkedHashMap() {
        System.out.println("Insertion Order Maintained");
        System.out.println();

        for (Map.Entry<Integer, Student> entry : linkedStudentMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue().getName());
        }
    }
}