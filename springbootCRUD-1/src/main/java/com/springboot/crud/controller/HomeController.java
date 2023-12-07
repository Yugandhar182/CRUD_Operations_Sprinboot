package com.springboot.crud.controller;

import com.springboot.crud.entity.Studentdata;
import org.springframework.http.HttpStatus;

import com.springboot.crud.service.StudentdataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private StudentdataService studentdataService;

    @ModelAttribute("student")
    public Studentdata studentdata() {
        return new Studentdata();
    }

    @GetMapping
    public String home(Model model) {
        try {
            Iterable<Studentdata> students = studentdataService.getAllStudents();
            model.addAttribute("students", students);
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately
        }
        return "form";
    }

    @PostMapping("/submitform")
    public String saveData(@ModelAttribute("student") Studentdata studentdata, Model model) {
        try {
            // Save the studentdata object using the service
            studentdataService.saveStudent(studentdata);

            // Get the updated list of all students from the service (including the newly saved one)
            List<Studentdata> allStudents = (List<Studentdata>) studentdataService.getAllStudents();

            // Add the updated list of students to the model for the "studentsdata.jsp" page
            model.addAttribute("students", allStudents);
            System.out.println(allStudents);
            // Add the student data to the model for the "studentsdata.jsp" page
            model.addAttribute("student", studentdata);

        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately, e.g., display an error message
        }

        return "studentsdata";
    }
    
    @GetMapping("/allstudents")
    @ResponseBody
    public List<Studentdata> getAllStudentsJson() {
        try {
            // Return the list of all students as JSON
            return (List<Studentdata>) studentdataService.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately, e.g., return an empty list or an error message
            return Collections.emptyList();
        }
    }
    
    @PostMapping("/addstudent")
    @ResponseBody
    public Studentdata addStudent(@RequestBody Studentdata studentdata) {
        try {
            // Save the studentdata object using the service
            return studentdataService.saveStudent(studentdata);
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately, e.g., return null or an error message
            return null;
        }
    }
    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id, Model model) {
        try {
            // Delete the student by ID
            studentdataService.deleteStudentById(id);

            // Get the updated list of all students
            List<Studentdata> allStudents = (List<Studentdata>) studentdataService.getAllStudents();

            // Add the updated list of students to the model for the "studentsdata.jsp" page
            model.addAttribute("students", allStudents);
            System.out.println(allStudents);

        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately, e.g., display an error message
        }

        return "studentsdata";
    }
    @PostMapping("/updateStudent/{id}")
    @ResponseBody
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Studentdata updatedStudent) {
        try {
            // Set the ID of the updated student
            updatedStudent.setId(id);

            // Update the student data using the service
            studentdataService.updateStudent(updatedStudent);

            // Get the updated list of all students
            List<Studentdata> allStudents = (List<Studentdata>) studentdataService.getAllStudents();

            return ResponseEntity.ok(allStudents); // Return the updated list of students

        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception using a logger
            // Handle the exception appropriately, e.g., return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating student: " + e.getMessage());
        }
    }


}
  

