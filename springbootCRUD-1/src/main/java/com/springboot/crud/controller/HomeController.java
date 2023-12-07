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


}
  

