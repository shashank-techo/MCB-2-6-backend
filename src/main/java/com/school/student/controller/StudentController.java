package com.school.student.controller;

import com.school.student.form.StudentForm;
import com.school.student.model.Student;
import com.school.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public List<Student> studentList(){
        return studentService.getAllStudents();
    }

    @PostMapping("/student-data")
    public Object studentData(@RequestBody StudentForm studentForm){
        studentService.saveStudent(studentForm);
        return new ResponseEntity("200", HttpStatus.OK);
    }


}
