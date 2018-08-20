package com.capgemini.domain;

import com.capgemini.Exceptions.IncorrectObjectException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity for employee.
 */
@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "OUTPOST_ID")
    private OutpostEntity outpost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE})
    @JoinColumn(name = "POSITION_ID", nullable = false)
    private PositionEntity position;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE})
    @JoinTable(name = "CARETAKERS",
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
        this.birthDate = new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay()).getTime();
        this.outpost = outpost;
        this.position = position;
        this.cars = cars;
    }

    public EmployeeEntity(String firstName, String lastName, Date birthDate, OutpostEntity outpost,
                          PositionEntity position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay()).getTime();
        this.outpost = outpost;
        this.position = position;
        this.cars = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay()).getTime();
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay())
                .getTime();
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

    public void addCar(CarEntity car) {
        this.cars.add(car);
    }

    public EmployeeEntity(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
        this.outpost = builder.outpost;
        this.position = builder.position;
        this.cars.addAll(builder.cars);
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Date birthDate;

        private OutpostEntity outpost;
        private PositionEntity position;
        private Set<CarEntity> cars = new HashSet<>();

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withBirthDate(Date birthDate) {
            this.birthDate = new GregorianCalendar(birthDate.getYear()+1900, birthDate.getMonth(), birthDate.getDay())
                    .getTime();
            return this;
        }

        public Builder withOutpost(OutpostEntity outpost) {
            this.outpost = outpost;
            return this;
        }

        public Builder withPosition(PositionEntity position) {
            this.position = position;
            return this;
        }

        public Builder withCars(Set<CarEntity> cars) {
            this.cars.addAll(cars);
            return this;
        }

        public EmployeeEntity build() {
            checkBeforeBuild();
            return new EmployeeEntity(this);
        }

        private void checkBeforeBuild() {
            if (firstName == null || lastName == null || birthDate == null) {
                throw new IncorrectObjectException("Incorrect employee to be created");
            }
        }
    }
}
