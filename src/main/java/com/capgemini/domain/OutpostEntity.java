package com.capgemini.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "OUTPOSTS")
public class OutpostEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ADDRESS", nullable = false, length = 200)
    private String addres;
    @Column(name = "CONTACT_DATA", nullable = false, length = 200)
    private String contactData;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outpost")
    private Set<EmployeeEntity> employees = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "startOutpost")
    private Set<RentalEntity> startRentals = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "endOutpost")
    private Set<RentalEntity> endRentals = new HashSet<>();

    public OutpostEntity() {
    }

    public OutpostEntity(String addres, String contactData, Set<EmployeeEntity> employees,
                         Set<RentalEntity> startRentals, Set<RentalEntity> endRentals) {
        this.addres = addres;
        this.contactData = contactData;
        this.employees = employees;
        this.startRentals = startRentals;
        this.endRentals = endRentals;
    }

    public OutpostEntity(String addres, String contactData) {
        this.addres = addres;
        this.contactData = contactData;
        this.employees = new HashSet<>();
        this.startRentals = new HashSet<>();
        this.endRentals = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    public void addEmployee(EmployeeEntity employee) {
        this.employees.add(employee);
        employee.setOutpost(this);
    }

    public void removeEmployee(EmployeeEntity employee) {
        this.employees.remove(employee);
        employee.setOutpost(null);
    }
}
