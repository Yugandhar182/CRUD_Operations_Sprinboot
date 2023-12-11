package com.springboot.crud.service.impl;

import com.springboot.crud.entity.Studentdata;
import com.springboot.crud.repositary.StudentdataRepository;
import com.springboot.crud.service.StudentdataService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentdataServiceImpl implements StudentdataService {

    @Autowired
    private StudentdataRepository studentdataRepository;

    @Override
    public Iterable<Studentdata> getAllStudents() {
        try {
            return studentdataRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately
            return null; // or throw a custom exception
        }
    }

    @Override
    public Studentdata saveStudent(Studentdata studentdata) {
        try {
            return studentdataRepository.save(studentdata);
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately
            return null; // or throw a custom exception
        }
    }
    @Override
    public void deleteStudentById(int id) {
        // Implementation to delete a student by ID
        studentdataRepository.deleteById(id);
    }

    @Override
    public Studentdata addStudent(Studentdata studentdata) {
        // Implement the logic to add a student, e.g., validation, business logic, etc.
        // You can return the saved student data if needed
        return studentdataRepository.save(studentdata);
   
    }

    @Override
    public Studentdata updateStudent(Studentdata updatedStudent) {
        // Check if the student with the given ID exists
        Optional<Studentdata> existingStudentOptional = studentdataRepository.findById(updatedStudent.getId());

        if (existingStudentOptional.isPresent()) {
            // If the student exists, update the data
            Studentdata existingStudent = existingStudentOptional.get();
            existingStudent.setFirstName(updatedStudent.getFirstname());
            existingStudent.setLastName(updatedStudent.getLastname());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setMobile(updatedStudent.getMobile());

            // Save the updated student
            return studentdataRepository.save(existingStudent);
        } else {
            // Handle the case where the student with the given ID does not exist
            // You may throw an exception or return null, depending on your requirements
            return null;
        }
    }
}