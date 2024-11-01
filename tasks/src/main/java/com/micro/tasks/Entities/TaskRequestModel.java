package com.micro.tasks.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskRequestModel {

    private int id;
    private String taskDescription;
    private String progress;
    private String duration;
    private String attachmentFileName;
    private byte[] attachmentData;

    private int supervisor;
    private int student;

}

