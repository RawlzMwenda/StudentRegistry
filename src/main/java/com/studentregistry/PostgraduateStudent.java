package com.studentregistry;

public class PostgraduateStudent extends Student {
    public PostgraduateStudent(int id, String name, double gpa) {
        super(id, name, gpa);
    }

    @Override
    public String getStudentType() {
        return "Postgraduate";
    }
}
