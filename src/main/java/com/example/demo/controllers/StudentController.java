package com.example.demo.controllers;

import com.example.demo.models.Course;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping("/getAll")
    public List<Student	> getAllCourse(){
        return studentService.getAllStudent();
    }

    @PostMapping("/add")
    public String addNewCourse(@RequestBody Student student){
        return  studentService.addNewStudent(student);
    }

    @GetMapping("/find/{id}")
    public Optional<Student> findById(@PathVariable Integer id) throws ParseException {
        if (studentService.findById(id).equals(Optional.empty())){
            String sDate1="31/12/1998";
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            Course c1 = new Course();
            List<Course> listOfCourses = new ArrayList<>();
            listOfCourses.add(c1);
            Student s = new Student("Ahmad","salfet",75, 599234567L, (java.sql.Date) date1,"CE" , listOfCourses);
            studentService.addNewStudent(s);
            return Optional.of(s);
        } else {
            return  studentService.findById(id);
        }

    }
    @GetMapping(value = {"find" , "find/","delete/","delete"})
    public String getNull(){
        return "plz Write a id";
    }

    @PutMapping("update/{id}")
    public String updateById(@PathVariable Integer id ,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String address,
                             @RequestParam(required = false) Long phoneNumber) {
        return studentService.update(id, name, address, phoneNumber);
    }

    @DeleteMapping("delete/{id}")
    public String deletOne(@PathVariable Integer id) {
        return studentService.deletById(id);
    }

}