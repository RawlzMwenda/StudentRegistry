package com.studentregistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository<T extends Student> {
    private final List<T> students = new ArrayList<>();

    public void add(T student) {
        students.add(student);
    }

    public List<T> findAll() {
        return students.stream().toList();
    }

    public Optional<T> findById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }
}
