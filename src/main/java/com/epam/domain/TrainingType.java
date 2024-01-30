package com.epam.domain;

import jakarta.persistence.*;

@Entity
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    public TrainingType(String name) {
        this.name = name;
    }

    public TrainingType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
