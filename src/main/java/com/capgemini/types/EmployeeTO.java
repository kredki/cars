package com.capgemini.types;

import com.capgemini.domain.OutpostEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EmployeeTO {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    private OutpostEntity outpost;
    private PositionTO position;
    private Set<CarTO> cars = new HashSet<>();

    public EmployeeTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
        this.outpost = builder.outpost;
        this.position = builder.position;
        this.cars.addAll(builder.cars);
    }

    public class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private Date birthDate;

        private OutpostEntity outpost;
        private PositionTO position;
        private Set<CarTO> cars = new HashSet<>();

        public void withId(long id) {
            this.id = id;
        }

        public void withFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void withLastName(String lastName) {
            this.lastName = lastName;
        }

        public void withBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public void withOutpost(OutpostEntity outpost) {
            this.outpost = outpost;
        }

        public void withPosition(PositionTO position) {
            this.position = position;
        }

        public void withCars(Set<CarTO> cars) {
            this.cars.addAll(cars);
        }

        public EmployeeTO build() {
            checkBeforeBuild();
            return new EmployeeTO(this);
        }

        private void checkBeforeBuild() {
            if (firstName == null || lastName == null || birthDate == null) {
                throw new RuntimeException("Incorrect employee to be created");
            }
        }
    }
}
