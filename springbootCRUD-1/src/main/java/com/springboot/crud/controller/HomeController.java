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
        return "studentsdata";
    }

    
    @GetMapping("/students")
    public String getAllStudentsJson(Model model) {
    	return "studentsdata";


}
    @GetMapping("/allstudents")
    @ResponseBody
    public   List<Studentdata>  getAllStudentsJson() {
    	
        try {
            // Return the list of all students as JSON
        	  // Get the updated list of all students from the service (including the newly saved one)
            List<Studentdata> allStudents = (List<Studentdata>) studentdataService.getAllStudents();
           System.out.println(allStudents);
           return  allStudents;
            
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
           
}
}
}



