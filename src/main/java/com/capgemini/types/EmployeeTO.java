package com.capgemini.types;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class EmployeeTO {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    private OutpostTO outpost;
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

    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private Date birthDate;

        private OutpostTO outpost;
        private PositionTO position;
        private Set<CarTO> cars = new HashSet<>();

        public Builder withId(long id) {
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
            this.birthDate = birthDate;
            return this;
        }

        public Builder withOutpost(OutpostTO outpost) {
            this.outpost = outpost;
            return this;
        }

        public Builder withPosition(PositionTO position) {
            this.position = position;
            return this;
        }

        public Builder withCars(Set<CarTO> cars) {
            this.cars.addAll(cars);
            return this;
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
