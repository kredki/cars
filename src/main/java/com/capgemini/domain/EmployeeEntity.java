package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTPOST_ID")
    private OutpostEntity outpost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID", nullable = false)
    private PositionEntity position;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CARETAKER",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "CAR_ID", nullable = false, updatable = false)}
    )
    private Set<CarEntity> cars = new HashSet<>();

    public EmployeeEntity() {
    }

    public EmployeeEntity(String firstName, String lastName, Date birthDate, OutpostEntity outpost,
                          PositionEntity position, Set<CarEntity> cars) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.outpost = outpost;
        this.position = position;
        this.cars = cars;
    }

    public EmployeeEntity(String firstName, String lastName, Date birthDate, OutpostEntity outpost,
                          PositionEntity position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.outpost = outpost;
        this.position = position;
        this.cars = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public OutpostEntity getOutpost() {
        return outpost;
    }

    public void setOutpost(OutpostEntity outpost) {
        this.outpost = outpost;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }
}
