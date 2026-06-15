package com.codealpha;
import java.util.ArrayList;
import java.util.Scanner;
class Student {
private String name;
private double grade;
public Student(String name, double grade) {
this.name = name;
this.grade = grade;
      }
    public String getName() {
        return name;
    }
    public double getGrade() {
        return grade;
    }
}
public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= n; i++) {
           System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Grade: ");
            double grade = sc.nextDouble();
            sc.nextLine();
            students.add(new Student(name, grade));
        }
        double total = 0;
        double highest = students.get(0).getGrade();
        double lowest = students.get(0).getGrade();
        for (Student s : students) {
            total += s.getGrade();
            if (s.getGrade() > highest)
                highest = s.getGrade();
            if (s.getGrade() < lowest)
                lowest = s.getGrade();
        }
        double average = total / students.size();
        System.out.println("\n===== REPORT =====");
        for (Student s : students) {
            System.out.println(s.getName() + " : " + s.getGrade());
        }
        System.out.println("\nAverage Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);
        sc.close();
    }
}