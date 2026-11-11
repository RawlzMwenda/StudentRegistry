package com.studentregistry;

public class Student {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        setName(name);
        setGpa(gpa);
        this.id = id;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        this.name = name.trim();
    }

    public void setGpa(double gpa) {
        if (!Double.isFinite(gpa) || gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        }
        this.gpa = gpa;
    }

    public String getStudentType() { return "Undergraduate"; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | GPA: %.2f | Type: %s",
                id, name, gpa, getStudentType());
    }
}
