package com.micro.tasks.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "evaluations")
public class Evaluation {

    private int id;
    private int idTutor;
    private int idStage;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public int getIdStage() {
        return idStage;
    }

    public void setIdStage(int idStage) {
        this.idStage = idStage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
