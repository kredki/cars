package com.capgemini.domain;

import com.capgemini.Exceptions.IncorrectObjectException;

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
    private Long id;
    @Embedded
    private AddressEnity address;
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

    public OutpostEntity(AddressEnity address, String contactData, Set<EmployeeEntity> employees,
                         Set<RentalEntity> startRentals, Set<RentalEntity> endRentals) {
        this.address = address;
        this.contactData = contactData;
        this.employees = employees;
        this.startRentals = startRentals;
        this.endRentals = endRentals;
    }

    public OutpostEntity(AddressEnity address, String contactData) {
        this.address = address;
        this.contactData = contactData;
        this.employees = new HashSet<>();
        this.startRentals = new HashSet<>();
        this.endRentals = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressEnity getAddress() {
        return address;
    }

    public void setAddress(AddressEnity address) {
        this.address = address;
    }

    public String getContactData() {
        return contactData;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public Set<RentalEntity> getStartRentals() {
        return startRentals;
    }

    public void setStartRentals(Set<RentalEntity> startRentals) {
        this.startRentals = startRentals;
    }

    public Set<RentalEntity> getEndRentals() {
        return endRentals;
    }

    public void setEndRentals(Set<RentalEntity> endRentals) {
        this.endRentals = endRentals;
    }

    public void addEmployee(EmployeeEntity employee) {
        this.employees.add(employee);
        employee.setOutpost(this);
    }

    public void removeEmployee(EmployeeEntity employee) {
        this.employees.remove(employee);
        employee.setOutpost(null);
    }

    public OutpostEntity(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.contactData = builder.contactData;
        this.employees = builder.employees;
        this.startRentals.addAll(builder.startRentals);
        this.endRentals.addAll(builder.endRentals);
    }

    public static class Builder {
        private Long id;
        private AddressEnity address;
        private String contactData;

        private Set<EmployeeEntity> employees = new HashSet<>();
        private Set<RentalEntity> startRentals = new HashSet<>();
        private Set<RentalEntity> endRentals = new HashSet<>();

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withAddress(AddressEnity address) {
            this.address = address;
            return this;
        }

        public Builder withContactData(String  contactData) {
            this.contactData = contactData;
            return this;
        }

        public Builder withEmployees(Set<EmployeeEntity> employees) {
            this.employees.addAll(employees);
            return this;
        }

        public Builder withStartRentals(Set<RentalEntity> startRentals) {
            this.startRentals.addAll(startRentals);
            return this;
        }

        public Builder withEndRentals(Set<RentalEntity> endRentals) {
            this.endRentals.addAll(endRentals);
            return this;
        }

        public OutpostEntity build() {
            checkBeforeBuild();
            return new OutpostEntity(this);
        }

        private void checkBeforeBuild() {
            if (address == null || contactData == null) {
                throw new IncorrectObjectException("Incorrect outpost to be created");
            }
        }
    }
}
