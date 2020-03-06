package com.school.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String id;
    private String studentId;
    private String studentName;
    private String level;
    private List<String> subjects;
}
