package com.example.demo.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Note {

    @Id
    @GeneratedValue
    @Column
    private UUID id;
    @Column
    private String title;
    @Column
    private String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Note() {}

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
