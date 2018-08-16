package com.capgemini.types;

import com.capgemini.Exceptions.IncorrectObjectException;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeTO {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public EmployeeTO(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthDate = builder.birthDate;
    }

    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private Date birthDate;

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

        public EmployeeTO build() {
            checkBeforeBuild();
            return new EmployeeTO(this);
        }

        private void checkBeforeBuild() {
            if (firstName == null || lastName == null || birthDate == null) {
                throw new IncorrectObjectException("Incorrect employee to be created");
            }
        }
    }
}
