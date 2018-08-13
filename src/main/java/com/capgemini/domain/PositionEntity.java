package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "POSITIONS")
public class PositionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
    private Set<EmployeeEntity> employees = new HashSet<>();

    public PositionEntity() {
    }

    public PositionEntity(String name, Set<EmployeeEntity> employees) {
        this.name = name;
        this.employees = employees;
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
