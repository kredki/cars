package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
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

//    position_id INT NOT NULL,
//    outpost_id INT NOT NULL,


    public EmployeeEntity() {
    }

    public EmployeeEntity(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
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
}
