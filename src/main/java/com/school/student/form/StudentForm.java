package com.school.student.form;

import lombok.Data;

import java.util.List;

@Data
public class StudentForm {
    private String studentName;
    private String level;
    private List<String> subjects;
}
