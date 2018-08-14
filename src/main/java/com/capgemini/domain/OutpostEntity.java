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
    @Embedded
    private Address address;
    @Column(name = "CONTACT_DATA", nullable = false, length = 200)
    private String contactData;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outpost", cascade = CascadeType.ALL)
    private Set<EmployeeEntity> employees = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "startOutpost", cascade = CascadeType.ALL)
    private Set<RentalEntity> startRentals = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "endOutpost", cascade = CascadeType.ALL)
    private Set<RentalEntity> endRentals = new HashSet<>();

    public OutpostEntity() {
    }

    public OutpostEntity(Address address, String contactData, Set<EmployeeEntity> employees,
                         Set<RentalEntity> startRentals, Set<RentalEntity> endRentals) {
        this.address = address;
        this.contactData = contactData;
        this.employees = employees;
        this.startRentals = startRentals;
        this.endRentals = endRentals;
    }

    public OutpostEntity(Address address, String contactData) {
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
