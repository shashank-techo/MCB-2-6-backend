package com.school.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String id;
    private String title;
    private String description;
    private Date createdOn;
    private String createdOnStr;
    private boolean completed;
    private Date completedOn;
    private boolean isDeleted;
}
