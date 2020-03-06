package com.school.student.service;

import com.school.student.form.StudentForm;
import com.school.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private MongoTemplate mongoTemplate;


    public List getAllStudents(){
        return mongoTemplate.findAll(Student.class);
    }

    public String saveStudent(StudentForm studentForm){

        Student student = new Student();
        student.setId(UUID.randomUUID().toString());
        student.setStudentId(studentForm.getStudentName().substring(0,1).toUpperCase()+(mongoTemplate.findAll(Student.class).size()+1));
        student.setStudentName(studentForm.getStudentName());
        student.setLevel(studentForm.getLevel());
        student.setSubjects(studentForm.getSubjects());
        mongoTemplate.save(student,"Student");
        return "SUCCESS";
    }
}
