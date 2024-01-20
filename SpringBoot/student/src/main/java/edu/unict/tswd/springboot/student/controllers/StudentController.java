package edu.unict.tswd.springboot.student.controllers;

import edu.unict.tswd.springboot.student.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students") 
public class StudentController {

    private static List<Student> students = new ArrayList(); //statico per definire un'entità singola 
    // Define a static block
    static {
        students.add(new Student(1,"Harry Potter"));
        students.add(new Student(2,"Hermione"));
    }

    @GetMapping //getStudent 
    public String getAllStudents(Model model) {
        model.addAttribute("students", students);
        return "students"; // Name of the template or models corrispondente
    }
}