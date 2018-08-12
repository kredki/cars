package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "POSITIONS")
public class PositionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    public PositionEntity() {
    }

    public PositionEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
