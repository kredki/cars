package com.capgemini.domain;

import com.capgemini.Exceptions.IncorrectObjectException;

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

    public PositionEntity(String name) {
        this.name = name;
        this.employees = new HashSet<>();
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

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public PositionEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.employees = builder.employees;
    }

    public static class Builder {
        private long id;
        private String name;

        private Set<EmployeeEntity> employees = new HashSet<>();

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmployees(Set<EmployeeEntity> employees) {
            this.employees.addAll(employees);
            return this;
        }

        public PositionEntity build() {
            checkBeforeBuild();
            return new PositionEntity(this);
        }

        private void checkBeforeBuild() {
            if (name == null) {
                throw new IncorrectObjectException("Incorrect position to be created");
            }
        }
    }
}
