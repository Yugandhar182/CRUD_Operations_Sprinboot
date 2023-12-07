package com.springboot.crud.service;

import com.springboot.crud.entity.Studentdata;

public interface StudentdataService {

    Iterable<Studentdata> getAllStudents();

    Studentdata saveStudent(Studentdata studentdata);

    void deleteStudentById(int id);

  
    // New method for adding a student
    Studentdata addStudent(Studentdata studentdata);

    Studentdata updateStudent(Studentdata updatedStudent);
}
